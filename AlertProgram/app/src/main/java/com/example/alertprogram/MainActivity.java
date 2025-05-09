package com.example.alertprogram;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button to show the custom Exit confirmation AlertDialog
        Button showDialogButton = findViewById(R.id.showDialogButton);

        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the custom layout for the confirmation dialog
                View customLayout = getLayoutInflater().inflate(R.layout.custom_alert_layout, null);

                // Access elements from the custom layout (optional)
                TextView alertTitle = customLayout.findViewById(R.id.alertTitle);
                TextView alertMessage = customLayout.findViewById(R.id.alertMessage);
                ImageView alertImage = customLayout.findViewById(R.id.alertImage);
                //Button alertButton = customLayout.findViewById(R.id.alertButton);

                // Modify the content (optional)
                alertTitle.setText("Exit Confirmation");
                alertMessage.setText("Do you really want to exit the app?");
                alertImage.setImageResource(R.drawable.ic_warning); // Replace with your own image

                // Build and show the AlertDialog with custom layout
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(customLayout);  // Set the custom layout
                builder.setCancelable(false);   // Make the dialog non-cancellable by tapping outside

                // Create the alert dialog
                alertDialog = builder.create();

                // Add "Yes" button to exit
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", (dialog, which) -> {
                    // Close the application when "Yes" is clicked
                    finish(); // Close the activity (app will exit if it's the only activity)
                });

                // Add "No" button to stay in the activity
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", (dialog, which) -> {
                    // Dismiss the dialog and stay in the app
                    dialog.dismiss();
                });

                // Show the dialog
                alertDialog.show();
            }
        });
    }
}
