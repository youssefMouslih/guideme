package com.ysf.mslh.guideme.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.ysf.mslh.guideme.R;
import com.ysf.mslh.guideme.adapters.ViewPagerAdapter;
import com.ysf.mslh.guideme.fragmentsExperience.ExperienceAbout;
import com.ysf.mslh.guideme.fragmentsExperience.ExperienceMap;
import com.ysf.mslh.guideme.fragmentsExperience.ExperiencePhotos;
import com.ysf.mslh.guideme.fragmentsExperience.ExperienceReviews;
import com.ysf.mslh.guideme.fragmentsExperience.ExperienceVideos;

public class experience extends Fragment {

    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_experience, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialisation des vues
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        // Initialisation de l'adaptateur pour le ViewPager
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        // Ajouter des fragments et leurs titres
        adapter.addFragment(new ExperienceAbout(), "About");
        adapter.addFragment(new ExperienceReviews(), "Reviews");
        adapter.addFragment(new ExperiencePhotos(), "Photos");
        adapter.addFragment(new ExperienceVideos(), "Videos");
        adapter.addFragment(new ExperienceMap(), "Map");

        // Définir l'adaptateur pour le ViewPager
        viewPager.setAdapter(adapter);

        // Lier le SlidingTabLayout avec le ViewPager
        tabLayout.setViewPager(viewPager);
    }
}
