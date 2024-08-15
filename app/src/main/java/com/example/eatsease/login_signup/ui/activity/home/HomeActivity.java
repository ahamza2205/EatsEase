package com.example.eatsease.login_signup.ui.activity.home;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.eatsease.R;
import com.example.eatsease.databinding.ActivityHomeBinding;
import com.example.eatsease.login_signup.ui.fragment.CalenderFragment;
import com.example.eatsease.login_signup.ui.fragment.FavoriteFragment;
import com.example.eatsease.login_signup.ui.fragment.HomeFragment;
import com.example.eatsease.login_signup.ui.fragment.SearchFragment;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Set the default fragment
        replaceFragment(new HomeFragment());

        // Set up the BottomNavigationView
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.search)
            {
                selectedFragment = new SearchFragment();
            } else if (item.getItemId() == R.id.favorite) {
                selectedFragment = new FavoriteFragment();
            } else if (item.getItemId() == R.id.calender) {
                selectedFragment = new CalenderFragment();
            }
            if (selectedFragment != null) {
                replaceFragment(selectedFragment);
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        // Optional: Add to back stack
        fragmentTransaction.commit();
    }
}
