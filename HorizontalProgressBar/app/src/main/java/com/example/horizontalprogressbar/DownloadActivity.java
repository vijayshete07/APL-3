package com.example.horizontalprogressbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DownloadActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private Button exitButton;
    private Handler handler = new Handler();
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);
        exitButton = findViewById(R.id.exitButton);
        exitButton.setVisibility(View.INVISIBLE); // Initially hide the exit button

        // Start the download simulation
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus++;

                    // Update the progress bar and text on the UI thread
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            progressText.setText("Downloading... " + progressStatus + "%");
                        }
                    });

                    try {
                        // Simulate download time
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Once download is complete, show the exit button
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressText.setText("Download Complete!");
                        exitButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).start();

        // Set up the exit button click listener to return to MainActivity
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity when the exit button is clicked
                Intent intent = new Intent(DownloadActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish this activity
            }
        });
    }
}
