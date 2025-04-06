package com.ysf.mslh.guideme.hiddenFragments;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ysf.mslh.guideme.R;
import com.ysf.mslh.guideme.adapters.ViewPagerAdapter;
import com.ysf.mslh.guideme.fragmentsExperience.ExperienceAbout;
import com.ysf.mslh.guideme.fragmentsExperience.ExperienceMap;
import com.ysf.mslh.guideme.fragmentsExperience.ExperiencePhotos;
import com.ysf.mslh.guideme.fragmentsExperience.ExperienceReviews;
import com.ysf.mslh.guideme.fragmentsExperience.ExperienceVideos;

public class ExperienceBottomSheet extends BottomSheetDialogFragment {

    public static ExperienceBottomSheet newInstance() {
        return new ExperienceBottomSheet();
    }
    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_experience_bottom_sheet, container, false);
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
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        if (view != null) {
            // Set height to 70% of screen
            ViewGroup.LayoutParams params = view.getLayoutParams();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenHeight = displayMetrics.heightPixels;
            params.height = (int) (screenHeight * 0.7);
            view.setLayoutParams(params);

            // Apply bottom margin -20dp
            ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginParams.bottomMargin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, -20, getResources().getDisplayMetrics());
            view.setLayoutParams(marginParams);
        }

        // Lock the bottom sheet so it cannot collapse
        View bottomSheet = getDialog().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (bottomSheet != null) {
            BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED); // Always expanded
            behavior.setSkipCollapsed(true);
            behavior.setHideable(false);
            behavior.setDraggable(false);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme);

    }
}
