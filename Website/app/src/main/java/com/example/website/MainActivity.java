package com.example.website;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText websiteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        websiteEditText = findViewById(R.id.editTextWebsite);
    }

    // This method is called when the "Open Website" button is clicked
    public void openWebsite(View view) {
        String website = websiteEditText.getText().toString().trim();

        if (!website.isEmpty()) {
            // Ensure the URL starts with "http://" or "https://"
            if (!website.startsWith("http://") && !website.startsWith("https://")) {
                website = "http://" + website; // Add "http://" if not present
            }

            // Create an implicit intent to open the URL in a browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
            startActivity(intent);
        } else {
            // Show a toast message if the website is empty
            Toast.makeText(this, "Please enter a website URL", Toast.LENGTH_SHORT).show();
        }
    }
}
