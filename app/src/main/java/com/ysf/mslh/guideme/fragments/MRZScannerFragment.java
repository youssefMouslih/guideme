
package com.ysf.mslh.guideme.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MRZScannerFragment extends Fragment {
    private PreviewView previewView;
    private ExecutorService cameraExecutor;
    private TextRecognizer textRecognizer;
    private TextView mrzResultText;
    private Paint cardOverlayPaint;
    private RectF lastDetectedCardBounds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mrz_scanner, container, false);
        previewView = view.findViewById(R.id.viewFinder);
        mrzResultText = view.findViewById(R.id.mrzResultText);

        setupCardOverlayPaint();
        initializeCamera();

        return view;
    }

    private void setupCardOverlayPaint() {
        cardOverlayPaint = new Paint();
        cardOverlayPaint.setColor(Color.GREEN);
        cardOverlayPaint.setStyle(Paint.Style.STROKE);
        cardOverlayPaint.setStrokeWidth(4f);
    }

    private void initializeCamera() {
        cameraExecutor = Executors.newSingleThreadExecutor();
        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 10);
        }
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = 
            ProcessCameraProvider.getInstance(requireContext());

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindCameraUseCases(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(requireContext()));
    }

    private void bindCameraUseCases(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();

        imageAnalysis.setAnalyzer(cameraExecutor, this::analyzeMRZ);

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);
    }

    private void analyzeMRZ(ImageProxy image) {
        InputImage inputImage = InputImage.fromMediaImage(
            image.getImage(),

            image.getImageInfo().getRotationDegrees()
        );

        textRecognizer.process(inputImage)
            .addOnSuccessListener(text -> {
                String mrzText = extractMRZText(text);
                if (mrzText != null) {
                    updateMRZResult(mrzText);
                    updateCardOverlay(text);
                } else {
                    clearCardOverlay();
                }
            })
            .addOnCompleteListener(task -> image.close());
    }

    private String extractMRZText(Text text) {
        // Pattern for MRZ format (basic example)
        Pattern mrzPattern = Pattern.compile("^[A-Z0-9<]{44}$", Pattern.MULTILINE);
        
        for (Text.TextBlock block : text.getTextBlocks()) {
            Matcher matcher = mrzPattern.matcher(block.getText());
            if (matcher.find()) {
                return block.getText();
            }
        }
        return null;
    }

    private void updateMRZResult(String mrzText) {
        requireActivity().runOnUiThread(() -> {
            mrzResultText.setText("MRZ Data:\n" + parseMRZData(mrzText));
        });
    }

    private String parseMRZData(String mrzText) {
        // Basic MRZ parsing (customize based on document type)
        StringBuilder parsed = new StringBuilder();
        parsed.append("Document Type: ").append(mrzText.substring(0, 2)).append("\n");
        parsed.append("Issuing Country: ").append(mrzText.substring(2, 5)).append("\n");
        parsed.append("Document Number: ").append(mrzText.substring(5, 14)).append("\n");
        // Add more fields as needed
        return parsed.toString();
    }

    private void updateCardOverlay(Text text) {
        RectF documentBounds = null;
        for (Text.TextBlock block : text.getTextBlocks()) {
            if (block.getText().matches(".*[A-Z0-9<]{44}.*")) {
                documentBounds = new RectF(block.getBoundingBox());
                // Expand bounds to approximate full document size
                documentBounds.top -= documentBounds.height() * 2;
                break;
            }
        }
        lastDetectedCardBounds = documentBounds;
        previewView.invalidate();
    }

    private void clearCardOverlay() {
        lastDetectedCardBounds = null;
        previewView.invalidate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cameraExecutor.shutdown();
    }
}
