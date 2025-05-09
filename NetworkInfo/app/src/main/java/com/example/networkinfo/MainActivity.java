package com.example.networkinfo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View; // <-- This is the missing import statement
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textViewOperatorName, textViewNetworkType, textViewOperatorInfo;
    private TelephonyManager telephonyManager;

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        textViewOperatorName = findViewById(R.id.textViewOperatorName);
        textViewNetworkType = findViewById(R.id.textViewNetworkType);
        textViewOperatorInfo = findViewById(R.id.textViewOperatorInfo);

        // Initialize system services
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        // Check permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_REQUEST_CODE);
        } else {
            // If permission is already granted, load network info
            loadNetworkInfo();
        }
    }

    // Method to load network information
    private void loadNetworkInfo() {
        // Get operator name
        String operatorName = telephonyManager.getNetworkOperatorName();
        textViewOperatorName.setText("Operator Name: " + operatorName);

        // Get network type (e.g., 2G, 3G, 4G, Wi-Fi)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        int networkType = telephonyManager.getNetworkType();
        String networkTypeStr = getNetworkTypeString(networkType);
        textViewNetworkType.setText("Network Type: " + networkTypeStr);

        // Get additional operator info
        String operatorInfo = "MCC: " + telephonyManager.getNetworkCountryIso() + " | MNC: " + telephonyManager.getNetworkOperator();
        textViewOperatorInfo.setText("Operator Info: " + operatorInfo);
    }

    // Method to convert network type integer to a string
    private String getNetworkTypeString(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "2G (GPRS)";
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "2G (EDGE)";
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "3G (UMTS)";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "4G (LTE)";
            case TelephonyManager.NETWORK_TYPE_NR:
                return "5G (NR)";

            default:
                return "Unknown";
        }
    }

    // Refresh button click handler
    public void refreshNetworkInfo(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            loadNetworkInfo();
        } else {
            Toast.makeText(this, "Permission denied. Please allow permission.", Toast.LENGTH_SHORT).show();
        }
    }

    // Handle permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadNetworkInfo();
            } else {
                Toast.makeText(this, "Permission denied. Unable to fetch network information.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
