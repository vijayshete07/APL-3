package com.example.explicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Page4 extends AppCompatActivity {

    private Button page2Button, page5Button, page3Button, page1Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        // Find buttons by ID
        page5Button = findViewById(R.id.page5Button);
        page2Button = findViewById(R.id.page2Button);
        page3Button = findViewById(R.id.page3Button);
        page1Button = findViewById(R.id.page1Button);

        // Set OnClickListener for each button to navigate to the corresponding page
        page5Button.setOnClickListener(v -> openPage(5));
        page2Button.setOnClickListener(v -> openPage(2));
        page3Button.setOnClickListener(v -> openPage(3));
        page1Button.setOnClickListener(v -> openPage(1));
    }

    // Method to open the corresponding page
    private void openPage(int pageNumber) {
        Intent intent;
        switch (pageNumber) {

            case 2:
                intent = new Intent(Page4.this, Page2.class);
                break;
            case 3:
                intent = new Intent(Page4.this, Page3.class);
                break;
            case 1:
                intent = new Intent(Page4.this, Page1.class);
                break;
            case 5:
                intent = new Intent(Page4.this, Page5.class);
                break;
            default:
                return;
        }
        startActivity(intent);
    }
}
