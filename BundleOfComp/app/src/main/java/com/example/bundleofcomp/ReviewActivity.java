package com.example.bundleofcomp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText suggestionsEditText, difficultiesEditText;
    private Button submitReviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        // Initialize views
        ratingBar = findViewById(R.id.ratingBar);
        suggestionsEditText = findViewById(R.id.suggestionsEditText);
        difficultiesEditText = findViewById(R.id.difficultiesEditText);
        submitReviewButton = findViewById(R.id.submitReviewButton);

        // Submit button click listener
        submitReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the rating value
                float rating = ratingBar.getRating();

                // Get the suggestions and difficulties text
                String suggestions = suggestionsEditText.getText().toString();
                String difficulties = difficultiesEditText.getText().toString();

                // Check if all fields are filled
                if (rating == 0 || suggestions.isEmpty() || difficulties.isEmpty()) {
                    Toast.makeText(ReviewActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Process the review data (For now, just show a toast message)
                    String reviewMessage = "Rating: " + rating + "\nSuggestions: " + suggestions + "\nDifficulties: " + difficulties;
                    Toast.makeText(ReviewActivity.this, "Review Submitted:\n" + reviewMessage, Toast.LENGTH_LONG).show();

                    // You can save this data to a database or send it to a server here.
                }
            }
        });
    }
}
