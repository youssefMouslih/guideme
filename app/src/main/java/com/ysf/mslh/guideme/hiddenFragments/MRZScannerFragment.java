package com.ysf.mslh.guideme.hiddenFragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.ysf.mslh.guideme.R;
import com.ysf.mslh.guideme.costumUi.MRZOverlayView;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MRZScannerFragment extends Fragment {
    private PreviewView previewView;
    private MRZOverlayView overlayView;
    private TextView mrzResultText;
    private ExecutorService cameraExecutor;
    private TextRecognizer textRecognizer;

    private static final Pattern MRZ_LINE_PATTERN = Pattern.compile("^[A-Z0-9<]{30,44}$");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mrz_scanner, container, false);
        previewView = view.findViewById(R.id.viewFinder);
        mrzResultText = view.findViewById(R.id.mrzResultText);
        overlayView = view.findViewById(R.id.overlayView);

        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        cameraExecutor = Executors.newSingleThreadExecutor();

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 10);
        }

        return view;
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> future = ProcessCameraProvider.getInstance(requireContext());
        future.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = future.get();
                bindCameraUseCases(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(requireContext()));
    }

    private void bindCameraUseCases(ProcessCameraProvider provider) {
        Preview preview = new Preview.Builder().build();
        CameraSelector selector = CameraSelector.DEFAULT_BACK_CAMERA;

        ImageAnalysis analysis = new ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();

        analysis.setAnalyzer(cameraExecutor, this::analyzeImage);

        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        provider.bindToLifecycle(this, selector, preview, analysis);
    }

    private void analyzeImage(ImageProxy image) {
        if (image.getImage() == null) {
            image.close();
            return;
        }

        InputImage inputImage = InputImage.fromMediaImage(image.getImage(), image.getImageInfo().getRotationDegrees());

        textRecognizer.process(inputImage)
                .addOnSuccessListener(this::handleTextResult)
                .addOnCompleteListener(task -> image.close());
    }

    private void handleTextResult(Text visionText) {
        StringBuilder mrzLines = new StringBuilder();
        Rect lastMRZRect = null;

        for (Text.TextBlock block : visionText.getTextBlocks()) {
            for (Text.Line line : block.getLines()) {
                String text = line.getText().trim();
                Matcher matcher = MRZ_LINE_PATTERN.matcher(text);
                if (matcher.find()) {
                    mrzLines.append(text).append("\n");

                    if (line.getBoundingBox() != null) {
                        lastMRZRect = line.getBoundingBox(); // Get last match's rect
                    }
                }
            }
        }

        if (mrzLines.length() > 0) {
            updateResult(mrzLines.toString());

            if (lastMRZRect != null) {
                // Get previewView's size to calculate transformation manually
                int previewWidth = previewView.getWidth();
                int previewHeight = previewView.getHeight();

                if (previewWidth > 0 && previewHeight > 0) {
                    // Map the bounding box coordinates to the preview view's coordinates
                    PointF previewPoint = new PointF(
                            (float) lastMRZRect.centerX() / previewWidth,
                            (float) lastMRZRect.centerY() / previewHeight
                    );

                    // Use the previewPoint to set bounds or update UI accordingly
                    overlayView.setBounds(new Rect(
                            (int) (previewPoint.x - lastMRZRect.width() / 2),
                            (int) (previewPoint.y - lastMRZRect.height() / 2),
                            (int) (previewPoint.x + lastMRZRect.width() / 2),
                            (int) (previewPoint.y + lastMRZRect.height() / 2)
                    ));
                }
            }
        } else {
            overlayView.clearBounds();
        }
    }

    private void updateResult(String mrzLine) {
        requireActivity().runOnUiThread(() -> {
            mrzResultText.setText("Detected MRZ Line:\n" + mrzLine);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cameraExecutor.shutdown();
    }
}
