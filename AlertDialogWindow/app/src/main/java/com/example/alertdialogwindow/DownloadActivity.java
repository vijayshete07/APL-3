package com.example.alertdialogwindow;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DownloadActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView percentText;
    private TextView downloadingText;
    private Button goBackButton;
    private int progressStatus = 0;
    private Handler handler = new Handler();  // Handler to update the progress on the UI thread

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        progressBar = findViewById(R.id.progressBar);
        percentText = findViewById(R.id.percentText);
        downloadingText = findViewById(R.id.textViewDownloading);
        goBackButton = findViewById(R.id.btnGoBack);

        // Start the download simulation
        simulateDownload();

        // Set an OnClickListener for the "Go Back" button
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and return to MainActivity
                finish();  // This will close the current activity and go back to the previous one (MainActivity)
            }
        });
    }

    private void simulateDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus++; // Increment the progress
                    try {
                        Thread.sleep(100); // Simulate delay for download (e.g., 100ms)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update progress bar and percentage text in the UI thread
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            percentText.setText(progressStatus + "%");
                        }
                    });
                }

                // Download complete - update UI
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Make progress bar invisible
                        progressBar.setVisibility(View.INVISIBLE);

                        // Change text to "Done"
                        downloadingText.setText("Download Complete");
                        percentText.setText("");  // Clear the percentage text

                        // Make the "Go Back" button visible
                        goBackButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).start(); // Start the thread to simulate the download
    }
}
