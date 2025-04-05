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
import com.ysf.mslh.guideme.fragments.ProfileFragment;
import com.ysf.mslh.guideme.fragments.experience;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener,experience.OnFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setBarBackgroundColor(R.color.white);

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

        bottomNavigationBar.setTabSelectedListener(this);

        // Afficher le premier fragment (Home)
        loadFragment(new HomeFragment());
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onTabSelected(int position) {
        Fragment selectedFragment = null;
        switch (position) {
            case 0:
                selectedFragment = new HomeFragment();
                break;
            case 1:
                selectedFragment = new CreditCardScannerFragment();
                break;
            case 2:
                selectedFragment = new CommunityFragment();
                break;
            case 3:
                selectedFragment = new ProfileFragment();
                break;
        }
        if (selectedFragment != null) {
            loadFragment(selectedFragment);
        }
    }

    @Override
    public void onTabUnselected(int position) {}

    @Override
    public void onTabReselected(int position) {}
    // This method is called from the experience fragment to switch to the Home tab
    @Override
    public void onBackToHomeTab() {
        // Change selected tab to the first tab (Home)
        BottomNavigationBar bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        if (bottomNavigationBar != null) {
            bottomNavigationBar.selectTab(0); // Select the Home tab
        }
    }

}
