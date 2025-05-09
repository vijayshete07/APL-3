package com.example.db;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, mobileNumberEditText;
    private Button saveButton, viewDataButton;
    private RecyclerView recyclerView;
    private DatabaseHelper dbHelper;
    private UserAdapter userAdapter;
    private ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        mobileNumberEditText = findViewById(R.id.mobileNumberEditText);
        saveButton = findViewById(R.id.saveButton);
        viewDataButton = findViewById(R.id.viewDataButton);
        recyclerView = findViewById(R.id.recyclerView);

        // Initialize the database helper
        dbHelper = new DatabaseHelper(this);

        // Initialize RecyclerView and Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList, new UserAdapter.OnUserActionListener() {
            @Override
            public void onDeleteClick(User user) {
                deleteUser(user);
            }

            @Override
            public void onUpdateClick(User user) {
                showUpdateDialog(user);
            }
        });
        recyclerView.setAdapter(userAdapter);

        // Save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user data from EditTexts
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String mobileNumber = mobileNumberEditText.getText().toString();

                // Validate input
                if (firstName.isEmpty() || lastName.isEmpty() || mobileNumber.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    insertUserData(firstName, lastName, mobileNumber);
                }
            }
        });

        // View Data button click listener
        viewDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve and display saved data from the database
                displaySavedData();
            }
        });
    }

    // Insert data into SQLite database
    private void insertUserData(String firstName, String lastName, String mobileNumber) {
        dbHelper.insertUser(firstName, lastName, mobileNumber);
        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
        displaySavedData();  // Refresh the list
    }

    // Display saved data in RecyclerView
    private void displaySavedData() {
        Cursor cursor = dbHelper.getAllUsers();

        userList.clear();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String firstName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME));
                String mobileNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MOBILE_NUMBER));

                userList.add(new User(id, firstName, lastName, mobileNumber));
            } while (cursor.moveToNext());
            cursor.close();
        }

        userAdapter.notifyDataSetChanged();  // Notify adapter that data has changed
    }

    // Delete a user
    private void deleteUser(User user) {
        dbHelper.deleteUser(user.getId());
        Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT).show();
        displaySavedData();  // Refresh the list after deletion
    }

    // Show a dialog to update user data
    private void showUpdateDialog(User user) {
        firstNameEditText.setText(user.getFirstName());
        lastNameEditText.setText(user.getLastName());
        mobileNumberEditText.setText(user.getMobileNumber());

        saveButton.setText("Update");

        // Set the button's click listener to update the user
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String mobileNumber = mobileNumberEditText.getText().toString();

                dbHelper.updateUser(user.getId(), firstName, lastName, mobileNumber);
                Toast.makeText(MainActivity.this, "User updated", Toast.LENGTH_SHORT).show();

                // Change the button back to "Save" after update
                saveButton.setText("Save");

                // Refresh the list
                displaySavedData();
            }
        });
    }
}
