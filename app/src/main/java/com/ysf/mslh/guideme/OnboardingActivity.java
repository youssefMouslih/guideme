package com.ysf.mslh.guideme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro2;
import com.github.appintro.AppIntroFragment;
import com.ysf.mslh.guideme.R;

public class OnboardingActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if onboarding has been completed before
        if (isOnboardingCompleted()) {
            navigateToLogin();
            finish();
            return;
        }

        // Add slides using AppIntroFragment.newInstance
        addSlide(AppIntroFragment.newInstance(
                "Guide Me",
                "Unlock Authentic Local Experiences\nNo More Scattered Searches",
                R.drawable.ic_launcher_background,
                Color.WHITE,
                Color.parseColor("#1A237E"),
                Color.parseColor("#1A237E")
        ));

        addSlide(AppIntroFragment.newInstance(
                "",
                "You can explore hidden gems, taste authentic local cuisine, and dive into unforgettable experiences unlike the adventure of a lifetime!",
                R.drawable.lacuisinemarocaine,
                Color.WHITE,
                Color.parseColor("#1A237E"),
                Color.parseColor("#1A237E")
        ));

        addSlide(AppIntroFragment.newInstance(
                "",
                "Plan your next adventure effortlessly, explore unique tours, uncover hidden gems, and experience unforgettable moments!",
                R.drawable.bg_button_rounded,
                Color.WHITE,
                Color.parseColor("#1A237E"),

                Color.parseColor("#1A237E")
        ));

        addSlide(AppIntroFragment.newInstance(
                "",
                "Share your journey, post your authentic local experiences, connect with fellow explorers, and inspire others to discover the heart of every destination!",
                R.drawable.ic_baseline_map_24,
                Color.WHITE,
                Color.parseColor("#1A237E"),
                Color.parseColor("#1A237E")
        ));

        // Customize appearance
        setIndicatorColor(
                Color.parseColor("#1A237E"), // Selected dot
                Color.parseColor("#BDBDBD")  // Unselected dot
        );

        // We don't need skip button
        setSkipButtonEnabled(false);
    }

    private boolean isOnboardingCompleted() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getBoolean("onboarding_completed", false);
    }

    private void setOnboardingCompleted() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("onboarding_completed", true);
        editor.apply();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDonePressed(@Nullable Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Mark onboarding as completed
        setOnboardingCompleted();
        navigateToLogin();
        finish();
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);

        // Set background color for different slides
        if (position > 0) {
            findViewById(com.github.appintro.R.id.bottom).setBackgroundColor(Color.parseColor("#1A237E"));
        } else {
            findViewById(com.github.appintro.R.id.bottom).setBackgroundColor(Color.WHITE);
        }
    }
}