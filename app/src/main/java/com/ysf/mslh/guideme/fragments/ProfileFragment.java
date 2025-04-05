package com.ysf.mslh.guideme.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.tabs.TabLayout;
import com.ysf.mslh.guideme.R;
import com.ysf.mslh.guideme.MediaAdapter;
import com.ysf.mslh.guideme.MediaItem;
import com.ysf.mslh.guideme.SampleDataGenerator;
import java.util.List;

/**
 * Profile Fragment that displays user profile in Instagram-like style
 * with profile picture, user info, and media tabs
 */
public class ProfileFragment extends Fragment {

    // UI Components
    private ImageView imgProfile;
    private TextView txtUsername;
    private TextView txtFullName;
    private Button btnEditProfile;
    private ImageButton btnAddPhoto;
    private ImageButton btnAdd;
    private ImageButton btnMenu;
    private TabLayout tabLayout;
    private RecyclerView recyclerViewMedia;

    // Sample data counts
    private static final int PHOTO_COUNT = 12;
    private static final int VIDEO_COUNT = 9;
    private static final int FAVORITE_COUNT = 6;

    // Sample statistics
    private static final int POSTS_COUNT = 27;
    private static final int FOLLOWERS_COUNT = 843;
    private static final int FOLLOWING_COUNT = 156;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     * @return A new instance of ProfileFragment
     */
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize UI components
        initViews(view);
        setupTabLayout();
        setupRecyclerView();

        return view;
    }

    private void initViews(View view) {
        // Find and initialize all view components from fragment_profile.xml
        imgProfile = view.findViewById(R.id.imgProfile);
        txtUsername = view.findViewById(R.id.txtUsername);
        txtFullName = view.findViewById(R.id.txtFullName);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        btnAddPhoto = view.findViewById(R.id.btnAddPhoto);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnMenu = view.findViewById(R.id.btnMenu);
        tabLayout = view.findViewById(R.id.tabLayout);
        recyclerViewMedia = view.findViewById(R.id.recyclerViewMedia);

        // Set initial data
        txtUsername.setText("Reda_1");
        txtFullName.setText("Reda Amine");

        // Set a background color for the profile image
        imgProfile.setBackgroundColor(Color.parseColor("#4FC3F7")); // Light blue background

        // Set click listeners
        btnEditProfile.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Edit profile clicked", Toast.LENGTH_SHORT).show();
        });

        btnAddPhoto.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add photo clicked", Toast.LENGTH_SHORT).show();
        });

        btnAdd.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add content clicked", Toast.LENGTH_SHORT).show();
        });

        btnMenu.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Menu clicked", Toast.LENGTH_SHORT).show();
        });
    }
    private void setupTabLayout() {
        // Configure the TabLayout from fragment_profile.xml
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Handle tab selection - change content based on selected tab
                int position = tab.getPosition();
                switch (position) {
                    case 0: // Photo tab
                        loadPhotoContent();
                        break;
                    case 1: // Video tab
                        loadVideoContent();
                        break;
                    case 2: // Favorite tab
                        loadFavoriteContent();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Not needed for basic implementation
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Not needed for basic implementation
            }
        });

        // Select the Video tab by default to match the reference image
        TabLayout.Tab videoTab = tabLayout.getTabAt(1);
        if (videoTab != null) {
            videoTab.select();
        }
    }

    private void setupRecyclerView() {
        // Set up RecyclerView grid with 3 columns (from fragment_profile.xml)
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerViewMedia.setLayoutManager(layoutManager);

        // Initially load video content as it's selected in the reference image
        loadVideoContent();
    }

    // Methods to load different content types based on tab selection
    private void loadPhotoContent() {
        List<MediaItem> photoItems = SampleDataGenerator.generatePhotoItems(PHOTO_COUNT);
        MediaAdapter adapter = new MediaAdapter(photoItems, false);
        recyclerViewMedia.setAdapter(adapter);
    }

    private void loadVideoContent() {
        List<MediaItem> videoItems = SampleDataGenerator.generateVideoItems(VIDEO_COUNT);
        MediaAdapter adapter = new MediaAdapter(videoItems, true);
        recyclerViewMedia.setAdapter(adapter);
    }

    private void loadFavoriteContent() {
        List<MediaItem> favoriteItems = SampleDataGenerator.generateFavoriteItems(FAVORITE_COUNT);
        MediaAdapter adapter = new MediaAdapter(favoriteItems, true);
        recyclerViewMedia.setAdapter(adapter);
    }
}