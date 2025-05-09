package com.example.bicyclerental;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    EditText rentalDurationEditText;
    Button confirmRentalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        rentalDurationEditText = findViewById(R.id.rentalDuration);
        confirmRentalButton = findViewById(R.id.confirmRentalButton);

        confirmRentalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rentalDuration = rentalDurationEditText.getText().toString();

                if (!rentalDuration.isEmpty()) {
                    int duration = Integer.parseInt(rentalDuration);
                    // Assume bike rental price is 10 per hour
                    int totalCost = duration * 10;

                    // Show booking confirmation
                    Toast.makeText(BookingActivity.this, "Rental Confirmed. Total cost: " + totalCost + " USD", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BookingActivity.this, "Please enter rental duration", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
