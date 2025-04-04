package com.ysf.mslh.guideme.fragmentsExperience;

import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.ysf.mslh.guideme.R;

import java.util.ArrayList;
import java.util.List;

public class ExperiencePhotos extends Fragment {
    private ImageSwitcher imageSwitcher;
    private List<Integer> imageResources;
    private int currentIndex = 0;
    private TextView indicatorTextView;  // Add a TextView to show the indicator
    private GestureDetector gestureDetector;

    public ExperiencePhotos() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience_photos, container, false);
        imageSwitcher = view.findViewById(R.id.imageSwitcher);
        indicatorTextView = view.findViewById(R.id.indicatorTextView);  // Find the indicator TextView

        // Set up the ImageSwitcher
        imageSwitcher.setFactory(() -> {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            return imageView;
        });

        imageResources = new ArrayList<>();
        // Add drawable resources here
        imageResources.add(R.drawable.ic_baseline_map_24);  // Replace with actual drawable resource IDs
        imageResources.add(R.drawable.ic_baseline_person_24);
        imageResources.add(R.drawable.ic_home);

        // Set up GestureDetector for swipe detection
        gestureDetector = new GestureDetector(requireContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // Detect swipe direction (left or right)
                if (e1.getX() - e2.getX() > 100) {
                    // Swipe left
                    showNextImage();
                } else if (e2.getX() - e1.getX() > 100) {
                    // Swipe right
                    showPreviousImage();
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        view.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));

        updateImage(); // Display the first image
        return view;
    }

    // Show the next image
    private void showNextImage() {
        currentIndex = (currentIndex + 1) % imageResources.size();
        updateImage();
    }

    // Show the previous image
    private void showPreviousImage() {
        currentIndex = (currentIndex - 1 + imageResources.size()) % imageResources.size();
        updateImage();
    }

    // Update the displayed image and the indicator
    private void updateImage() {
        Glide.with(requireContext())
                .load(imageResources.get(currentIndex))  // Load image from drawable
                .into((ImageView) imageSwitcher.getCurrentView());

        // Update the indicator
        updateIndicator();
    }

    // Update the indicator TextView with the current index (1-based index)
    private void updateIndicator() {
        String indicatorText = (currentIndex + 1) + "/" + imageResources.size();
        indicatorTextView.setText(indicatorText);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Clear any pending actions in the handler
    }
}
