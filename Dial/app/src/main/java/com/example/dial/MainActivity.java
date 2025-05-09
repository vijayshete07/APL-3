package com.example.dial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView numberDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberDisplay = findViewById(R.id.numberDisplay);

        // Number buttons
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0);
        Button buttonStar = findViewById(R.id.buttonStar);
        Button buttonHash = findViewById(R.id.buttonHash);

        // Add onClickListeners for buttons
        setButtonOnClickListener(button1, "1");
        setButtonOnClickListener(button2, "2");
        setButtonOnClickListener(button3, "3");
        setButtonOnClickListener(button4, "4");
        setButtonOnClickListener(button5, "5");
        setButtonOnClickListener(button6, "6");
        setButtonOnClickListener(button7, "7");
        setButtonOnClickListener(button8, "8");
        setButtonOnClickListener(button9, "9");
        setButtonOnClickListener(button0, "0");
        setButtonOnClickListener(buttonStar, "*");
        setButtonOnClickListener(buttonHash, "#");

        // Dial button
        Button dialButton = findViewById(R.id.dialButton);
        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = numberDisplay.getText().toString();
                if (!number.isEmpty()) {
                    dialNumber(number);
                }
            }
        });
    }

    // Method to handle button clicks and append numbers
    private void setButtonOnClickListener(Button button, final String digit) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberDisplay.append(digit);
            }
        });
    }

    // Method to dial the number
    private void dialNumber(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }
}
