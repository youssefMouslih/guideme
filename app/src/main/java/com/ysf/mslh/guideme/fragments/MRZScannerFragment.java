
package com.ysf.mslh.guideme.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
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
import com.googlecode.tesseract.android.TessBaseAPI;
import com.ysf.mslh.guideme.R;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MRZScannerFragment extends Fragment {
    private PreviewView previewView;
    private TextView resultText;
    private ExecutorService cameraExecutor;
    private TessBaseAPI tessBaseAPI;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mrz_scanner, container, false);
        
        previewView = view.findViewById(R.id.viewFinder);
        resultText = view.findViewById(R.id.resultText);
        
        // Initialize Tesseract
        initTesseract();
        
        // Request camera permission
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
        }
        
        cameraExecutor = Executors.newSingleThreadExecutor();
        
        return view;
    }
    
    private void initTesseract() {
        tessBaseAPI = new TessBaseAPI();
        // Initialize Tesseract with trained data for MRZ
        String dataPath = requireContext().getFilesDir() + "/tesseract/";
        tessBaseAPI.init(dataPath, "mrz");
    }
    
    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = 
            ProcessCameraProvider.getInstance(requireContext());
            
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(requireContext()));
    }
    
    private void bindPreview(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();
                
        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();
                
        imageAnalysis.setAnalyzer(cameraExecutor, this::analyzeMRZ);
        
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        
        cameraProvider.unbindAll();
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);
    }
    
    private void analyzeMRZ(ImageProxy image) {
        Bitmap bitmap = imageProxyToBitmap(image);
        tessBaseAPI.setImage(bitmap);
        
        String result = tessBaseAPI.getUTF8Text();
        if (isMRZFormat(result)) {
            requireActivity().runOnUiThread(() -> {
                resultText.setText(parseMRZ(result));
            });
        }
        
        image.close();
    }
    
    private Bitmap imageProxyToBitmap(ImageProxy image) {
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.capacity()];
        buffer.get(bytes);
        
        Bitmap bitmap = Bitmap.createBitmap(
            image.getWidth(), image.getHeight(), Bitmap.Config.ARGB_8888);
            
        // Convert YUV to RGB
        // ... (YUV to RGB conversion code here)
        
        return bitmap;
    }
    
    private boolean isMRZFormat(String text) {
        // Basic MRZ format check (can be enhanced)
        Pattern pattern = Pattern.compile("^[A-Z0-9<]{44}$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
    
    private String parseMRZ(String mrzText) {
        StringBuilder parsed = new StringBuilder();
        parsed.append("Document Type: ").append(mrzText.substring(0, 2)).append("\n");
        parsed.append("Country: ").append(mrzText.substring(2, 5)).append("\n");
        parsed.append("Document Number: ").append(mrzText.substring(5, 14)).append("\n");
        // Add more parsing logic for other fields
        return parsed.toString();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        cameraExecutor.shutdown();
        tessBaseAPI.end();
    }
}
