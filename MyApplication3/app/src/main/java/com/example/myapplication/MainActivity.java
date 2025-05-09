// src/com/yourapp/MainActivity.java
//package com.yourapp;
package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnExit;
    private RatingBar ratingBar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        btnExit = findViewById(R.id.btnExit);
        ratingBar = findViewById(R.id.ratingBar);
        progressBar = findViewById(R.id.progressBar);

        // Set up the Exit button click listener
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();
            }
        });

        // Example: Simulate loading and show the ProgressBar
        simulateLoading();
    }

    // Function to show the exit confirmation dialog
    private void showExitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Do you want to exit the app?")
                .setCancelable(false) // Prevent closing the dialog by tapping outside
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Exit the app if user clicks "Yes"
                        finish(); // Close the activity (exit the app)
                    }
                })
                .setNegativeButton("No", null) // Do nothing if user clicks "No"
                .show();
    }

    // Function to simulate loading and show the ProgressBar
    private void simulateLoading() {
        // Show progress bar to simulate loading
        progressBar.setVisibility(View.VISIBLE);

        // Simulate a delay for loading (e.g., network call or task)
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Simulate some loading process (e.g., 3 seconds)
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // After the "loading" is complete, hide the progress bar
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }
}
