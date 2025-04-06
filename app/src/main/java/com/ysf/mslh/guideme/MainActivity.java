package com.ysf.mslh.guideme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ysf.mslh.guideme.fragments.CommunityFragment;
import com.ysf.mslh.guideme.fragments.CreditCardScannerFragment;
import com.ysf.mslh.guideme.fragments.DiscoverFragment;
import com.ysf.mslh.guideme.fragments.HomeFragment;
import com.ysf.mslh.guideme.fragments.MRZScannerFragment;
import com.ysf.mslh.guideme.fragments.ProfileFragment;
import com.ysf.mslh.guideme.fragments.experience;

/**
 * Main activity class that handles the bottom navigation and fragment switching
 * Implements tab selection listener for navigation and fragment interaction
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener,experience.OnFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize BottomNavigationBar
        BottomNavigationBar bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        // Configure BottomNavigationBar
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setBarBackgroundColor(R.color.white);

        // Add items to BottomNavigationBar
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home, "Home")
                        .setActiveColorResource(R.color.main_color))
                .addItem(new BottomNavigationItem(R.drawable.ic_baseline_map_24, "Discover")
                        .setActiveColorResource(R.color.main_color))
                .addItem(new BottomNavigationItem(R.drawable.ic_baseline_people_24, "Community")
                        .setActiveColorResource(R.color.main_color))
                .addItem(new BottomNavigationItem(R.drawable.ic_baseline_person_24, "Profile")
                        .setActiveColorResource(R.color.main_color))
                .setFirstSelectedPosition(0)
                .initialise();

        // Set tab selection listener
        bottomNavigationBar.setTabSelectedListener(this);

        // Load the Home fragment initially
        loadFragment(new HomeFragment());
    }

    // Method to load a fragment into the fragment container
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    // Handle tab selection events
    @Override
    public void onTabSelected(int position) {
        Fragment selectedFragment = null;
        // Determine which fragment to load based on the selected tab position
        switch (position) {
            case 0:
                selectedFragment = new HomeFragment();
                break;
            case 1:
                selectedFragment = new MRZScannerFragment();
                break;
            case 2:
                selectedFragment = new CommunityFragment();
                break;
            case 3:
                selectedFragment = new ProfileFragment();
                break;
        }
        // Load the selected fragment
        if (selectedFragment != null) {
            loadFragment(selectedFragment);
        }
    }

    // Handle tab unselected events
    @Override
    public void onTabUnselected(int position) {}

    // Handle tab reselected events
    @Override
    public void onTabReselected(int position) {}

    // Callback method from the experience fragment to navigate back to the home tab
    @Override
    public void onBackToHomeTab() {
        // Find the BottomNavigationBar and select the first tab (Home)
        BottomNavigationBar bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        if (bottomNavigationBar != null) {
            bottomNavigationBar.selectTab(0); // Select the Home tab
        }
    }

}