// src/com/yourapp/AlertBar.java
package com.yourapp;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlertBar {

    private Context context;
    private LinearLayout alertBar;
    private TextView alertMessage;
    private ImageView closeButton;

    public AlertBar(Context context, LinearLayout alertBar) {
        this.context = context;
        this.alertBar = alertBar;
        this.alertMessage = alertBar.findViewById(R.id.alertMessage);
        this.closeButton = alertBar.findViewById(R.id.closeButton);

        // Close button to hide the alert bar
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAlertBar();
            }
        });
    }

    // Method to show the alert bar with a custom message
    public void showAlert(String message) {
        alertMessage.setText(message);
        alertBar.setVisibility(View.VISIBLE);
    }

    // Method to hide the alert bar
    public void hideAlertBar() {
        alertBar.setVisibility(View.GONE);
    }
}
