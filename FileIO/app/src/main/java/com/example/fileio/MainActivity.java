package com.example.fileio;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME_INTERNAL = "myfile_internal.txt"; // Internal storage file name

    private EditText editText; // EditText for user input
    private TextView textView; // TextView to display file content
    private RadioGroup radioGroup; // RadioGroup to choose append or overwrite

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        radioGroup = findViewById(R.id.radioGroup);

        // Button for writing data to internal storage
        Button writeButton = findViewById(R.id.writeButton);
        writeButton.setOnClickListener(v -> {
            String userInput = editText.getText().toString();
            if (!userInput.isEmpty()) {
                boolean append = isAppendSelected(); // Check if "Append" is selected
                writeToInternalStorage(userInput, append); // Write input text to internal storage
            } else {
                Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show();
            }
        });

        // Button for reading data from internal storage
        Button readButton = findViewById(R.id.readButton);
        readButton.setOnClickListener(v -> {
            String fileContents = readFromInternalStorage();
            textView.setText(fileContents); // Display content in TextView
        });
    }

    // Check if "Append" option is selected
    private boolean isAppendSelected() {
        RadioButton appendRadioButton = findViewById(R.id.appendRadio);
        return appendRadioButton.isChecked();
    }

    // Write to internal storage with option to append or overwrite
    private void writeToInternalStorage(String data, boolean append) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME_INTERNAL, append ? MODE_APPEND : MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(this, "File saved to internal storage", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error writing to file", Toast.LENGTH_SHORT).show();
        }
    }

    // Read from internal storage
    private String readFromInternalStorage() {
        String result = "";
        try {
            FileInputStream fis = openFileInput(FILENAME_INTERNAL);
            int character;
            while ((character = fis.read()) != -1) {
                result += (char) character;
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
