package com.example.myapplication;// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.BookingActivity;

public class MainActivity extends AppCompatActivity {

    EditText emailAddressEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize EditText fields
        emailAddressEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);

        // Add a click listener to the "Book Now" button
        Button bookButton = findViewById(R.id.buttonBook);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the user is logged in
                if (isLoggedIn()) {
                    // Start the BookingActivity if logged in
                    startBookingActivity();
                } else {
                    showToast("Please login first.");
                }
            }
        });
    }

    public void login(View view) {
        String email = emailAddressEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Hardcoded credentials for demonstration purposes
        String correctEmail = "user123";
        String correctPassword = "password123";

        // Check if the entered credentials are correct
        if (email.equals(correctEmail) && password.equals(correctPassword)) {
            showToast("Login Successful");

            // Start the BookingActivity after a successful login
            startBookingActivity();

        } else {
            showToast("Invalid credentials. Try again.");
        }
    }

    private void startBookingActivity() {
        Intent intent = new Intent(this, BookingActivity.class);
        startActivity(intent);
    }

    private boolean isLoggedIn() {
        // Check if the user is logged in (modify this based on your actual login mechanism)
        return true;  // For demonstration purposes, always return true
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
