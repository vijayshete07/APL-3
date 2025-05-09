package com.example.dial;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView dialedNumberTextView;
    private String dialedNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialedNumberTextView = findViewById(R.id.textViewDialedNumber);
    }

    // This method is called when a digit button is clicked
    public void onDigitClick(View view) {
        Button button = (Button) view;
        String digit = button.getText().toString();
        dialedNumber += digit;
        dialedNumberTextView.setText(dialedNumber);
    }

    // This method is called when the Call button is clicked
    public void onCallClick(View view) {
        if (!dialedNumber.isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + dialedNumber));
            // Check if the app has permission to make calls
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                } else {
                    // Handle permission request if needed
                }
            } else {
                startActivity(intent);
            }
        }
    }
}
