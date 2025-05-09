package com.example.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.implicitintent.R;

public class MainActivity extends AppCompatActivity {

    private EditText editText; // EditText to input website name
    private Button button;     // Button to trigger the intent
    private TextView resultTextView; // TextView to display the feedback

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views by ID
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.b1);
        resultTextView = findViewById(R.id.resultTextView);

        // Set OnClickListener for the button
        button.setOnClickListener(v -> {
            // Get the text entered in EditText
            String website = editText.getText().toString().trim();

            // Check if the EditText is empty
            if (!website.isEmpty()) {
                // Add "http://" if the user did not enter it
                if (!website.startsWith("http://") && !website.startsWith("https://")) {
                    website = "http://" + website;  // Prepend with "http://" if not present
                }

                // Create an implicit intent to view the website
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));

                // Start the activity with the intent
                startActivity(intent);

                // Provide feedback that the app is opening the website
                resultTextView.setText("Opening: " + website);
            } else {
                // Show error message if no input is entered
                resultTextView.setText("Please enter a valid website.");
            }
        });
    }
}
