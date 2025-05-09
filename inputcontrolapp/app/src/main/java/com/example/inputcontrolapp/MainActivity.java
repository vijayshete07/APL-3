package com.example.simpleapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private RadioGroup radioGroupGender;
    private CheckBox checkBoxTerms;
    private Spinner spinnerCountry;
    private MaterialButton submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        editTextName = findViewById(R.id.editTextName);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
        spinnerCountry = findViewById(R.id.spinnerCountry);
        submitButton = findViewById(R.id.submitButton);

        // Spinner Adapter (Simple Array)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[] {"USA", "India", "UK", "Canada"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);

        // Button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                int selectedRadioButtonId = radioGroupGender.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String gender = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "Not selected";
                boolean termsAccepted = checkBoxTerms.isChecked();
                String selectedCountry = spinnerCountry.getSelectedItem().toString();

                // Display the input data as a Toast
                String message = "Name: " + name + "\n" +
                        "Gender: " + gender + "\n" +
                        "Accepted Terms: " + (termsAccepted ? "Yes" : "No") + "\n" +
                        "Country: " + selectedCountry;
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
