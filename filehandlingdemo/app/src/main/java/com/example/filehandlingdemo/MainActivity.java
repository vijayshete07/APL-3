package com.example.filehandlingdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "myfile.txt";

    EditText editTextInput;
    TextView textViewOutput;
    Button buttonSave, buttonRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
        buttonSave = findViewById(R.id.buttonSave);
        buttonRead = findViewById(R.id.buttonRead);
        textViewOutput = findViewById(R.id.textViewOutput);

        buttonSave.setOnClickListener(v -> saveToFile());
        buttonRead.setOnClickListener(v -> readFromFile());
    }

    private void saveToFile() {
        String text = editTextInput.getText().toString();
        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(text.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving file", Toast.LENGTH_SHORT).show();
        }
    }

    private void readFromFile() {
        try (FileInputStream fis = openFileInput(FILE_NAME)) {
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            textViewOutput.setText(sb.toString().trim());

        } catch (IOException e) {
            e.printStackTrace();
            textViewOutput.setText("Error reading file");
        }
    }
}