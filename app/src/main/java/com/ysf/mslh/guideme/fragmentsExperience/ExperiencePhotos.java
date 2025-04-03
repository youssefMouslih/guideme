package com.ysf.mslh.guideme.fragmentsExperience;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.ysf.mslh.guideme.R;

import java.util.ArrayList;
import java.util.List;

public class ExperiencePhotos extends Fragment {
    private ImageSwitcher imageSwitcher;
    private List<String> imageUrls;
    private int currentIndex = 0;
    private Handler handler = new Handler();

    public ExperiencePhotos() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience_photos, container, false);
        imageSwitcher = view.findViewById(R.id.imageSwitcher);

        imageSwitcher.setFactory(() -> {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        });

        imageUrls = new ArrayList<>();
        imageUrls.add("https://i.postimg.cc/7Y8qNXyn/11162158-symboles-de-permis-de-conduire-vectoriel.jpg");
        imageUrls.add("https://example.com/image2.jpg");
        imageUrls.add("https://example.com/image3.jpg");

        startImageSwitcher();

        return view;
    }

    private void startImageSwitcher() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Glide.with(requireContext()).load(imageUrls.get(currentIndex)).into((ImageView) imageSwitcher.getCurrentView());
                currentIndex = (currentIndex + 1) % imageUrls.size();
                handler.postDelayed(this, 3000);
            }
        }, 3000);
    }
}
