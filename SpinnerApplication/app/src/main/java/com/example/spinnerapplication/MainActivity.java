package com.example.spinnerapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner languageSpinner;
    private TextView selectedLanguageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Spinner and TextView
        languageSpinner = findViewById(R.id.languageSpinner);
        selectedLanguageText = findViewById(R.id.selectedLanguage);

        // Create an ArrayAdapter to populate the Spinner with language options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.language_options, // Reference the string array
                android.R.layout.simple_spinner_item // Use simple spinner item layout
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        // Set an OnItemSelectedListener to handle selection
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected item as a string
                String selectedLanguage = parentView.getItemAtPosition(position).toString();

                // Display the selected language in the TextView
                selectedLanguageText.setText("Selected Language: " + selectedLanguage);

                // Show a Toast message with the selected language
                Toast.makeText(MainActivity.this, "You selected: " + selectedLanguage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle case when no item is selected (optional)
            }
        });
    }
}
