package com.example.explicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button page1Button, page2Button, page3Button, page4Button, page5Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the buttons by ID
        page1Button = findViewById(R.id.page1Button);
        page2Button = findViewById(R.id.page2Button);
        page3Button = findViewById(R.id.page3Button);
        page4Button = findViewById(R.id.page4Button);
        page5Button = findViewById(R.id.page5Button);

        // Set OnClickListener for each button
        page1Button.setOnClickListener(v -> openPage(1));
        page2Button.setOnClickListener(v -> openPage(2));
        page3Button.setOnClickListener(v -> openPage(3));
        page4Button.setOnClickListener(v -> openPage(4));
        page5Button.setOnClickListener(v -> openPage(5));
    }

    // Method to open the corresponding page
    private void openPage(int pageNumber) {
        Intent intent;
        switch (pageNumber) {
            case 1:
                intent = new Intent(MainActivity.this, Page1.class);
                break;
            case 2:
                intent = new Intent(MainActivity.this, Page2.class);
                break;
            case 3:
                intent = new Intent(MainActivity.this, Page3.class);
                break;
            case 4:
                intent = new Intent(MainActivity.this, Page4.class);
                break;
            case 5:
                intent = new Intent(MainActivity.this, Page5.class);
                break;
            default:
                return;
        }
        startActivity(intent);
    }
}
