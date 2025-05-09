package com.example.explicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Page2 extends AppCompatActivity {

    private Button page1Button, page5Button, page3Button, page4Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        // Find buttons by ID
        page5Button = findViewById(R.id.page5Button);
        page1Button = findViewById(R.id.page1Button);
        page3Button = findViewById(R.id.page3Button);
        page4Button = findViewById(R.id.page4Button);

        // Set OnClickListener for each button to navigate to the corresponding page
        page5Button.setOnClickListener(v -> openPage(5));
        page1Button.setOnClickListener(v -> openPage(1));
        page3Button.setOnClickListener(v -> openPage(3));
        page4Button.setOnClickListener(v -> openPage(4));
    }

    // Method to open the corresponding page
    private void openPage(int pageNumber) {
        Intent intent;
        switch (pageNumber) {

            case 1:
                intent = new Intent(Page2.this, Page1.class);
                break;
            case 3:
                intent = new Intent(Page2.this, Page3.class);
                break;
            case 4:
                intent = new Intent(Page2.this, Page4.class);
                break;
            case 5:
                intent = new Intent(Page2.this, Page5.class);
                break;
            default:
                return;
        }
        startActivity(intent);
    }
}
