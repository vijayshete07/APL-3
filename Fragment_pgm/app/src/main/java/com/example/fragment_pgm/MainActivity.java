package com.example.fragment_pgm;  // Ensure this matches your app's package name

import android.os.Bundle;
import android.view.View;  // Import the View class
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If this is the first time the activity is being created, load FragmentOne by default
        if (savedInstanceState == null) {
            Fragment fragment = new FragmentOne();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    // Method to switch fragments based on the button clicked
    public void switchFragment(View view) {
        Fragment fragment = null;

        // Check which button was clicked
        if (view.getId() == R.id.button1) {
            fragment = new FragmentOne();
        } else if (view.getId() == R.id.button2) {
            fragment = new FragmentTwo();
        }

        if (fragment != null) {
            // Replace the current fragment with the new one
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
