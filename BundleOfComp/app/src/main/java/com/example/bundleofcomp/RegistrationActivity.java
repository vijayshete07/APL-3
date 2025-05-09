package com.example.bundleofcomp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText firstName, lastName, email, collegeName;
    RadioGroup genderRadioGroup;
    Spinner subjectSpinner;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize views
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        collegeName = findViewById(R.id.collegeName);
        genderRadioGroup = findViewById(R.id.gender);
        subjectSpinner = findViewById(R.id.subjectSpinner);
        submitButton = findViewById(R.id.submitButton);

        // Set up the Spinner with subjects
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.subjects_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);

        // Submit Button Click Listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameValue = firstName.getText().toString();
                String lastNameValue = lastName.getText().toString();
                String emailValue = email.getText().toString();
                String collegeNameValue = collegeName.getText().toString();
                int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedGender = findViewById(selectedGenderId);
                String gender = selectedGender != null ? selectedGender.getText().toString() : "Not selected";
                String subject = subjectSpinner.getSelectedItem().toString();

                // For now, show a Toast with the data
                Toast.makeText(RegistrationActivity.this, "First Name: " + firstNameValue +
                        "\nLast Name: " + lastNameValue + "\nEmail: " + emailValue +
                        "\nGender: " + gender + "\nSubject: " + subject +
                        "\nCollege Name: " + collegeNameValue, Toast.LENGTH_LONG).show();
            }
        });
    }
}
