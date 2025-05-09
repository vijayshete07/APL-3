package com.example.togglebutton;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare the ToggleButton and TextView
    private ToggleButton toggleButton;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ToggleButton and TextView
        toggleButton = findViewById(R.id.toggleButton);
        //statusText = findViewById(R.id.statusText);

        // Set an event handler for the ToggleButton
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change TextView text when ToggleButton is ON
//                    statusText.setText("Toggle is ON");
                    // Show a Toast message when ToggleButton is ON
                    Toast.makeText(MainActivity.this, "Toggle is ON", Toast.LENGTH_SHORT).show();
                } else {
                    // Change TextView text when ToggleButton is OFF
                    //statusText.setText("Toggle is OFF");
                    // Show a Toast message when ToggleButton is OFF
                    Toast.makeText(MainActivity.this, "Toggle is OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
