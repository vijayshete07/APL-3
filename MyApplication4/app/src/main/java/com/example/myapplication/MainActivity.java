// src/com/yourapp/MainActivity.java
package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private com.yourapp.AlertBar alertBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get reference to the custom alert bar layout
        LinearLayout customAlertBarLayout = findViewById(R.id.alertBar);

        // Initialize the AlertBar class
        alertBar = new com.yourapp.AlertBar(this, customAlertBarLayout);

        // Show the alert bar with a custom message
        alertBar.showAlert("This is a custom alert message!");
    }

    // Method to hide the alert bar (called by a button or some other event)
    public void hideAlert(View view) {
        alertBar.hideAlertBar();
    }
}
