// MainActivity.java
package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        Button signUpButton = findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignupActivity();
            }
        });

        // Add a click listener to the "Login" button
        Button loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String email = emailAddressEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Retrieve stored user credentials from SharedPreferences
        String storedEmail = getUserEmailFromSharedPreferences();
        String storedPassword = getUserPasswordFromSharedPreferences();

        // Check if the entered credentials match the stored credentials
        if (email.equals(storedEmail) && password.equals(storedPassword)) {
            showToast("Login Successful");

            // Start the BookingActivity after a successful login
            startBookingActivity();

        } else {
            showToast("Invalid credentials. Try again.");
        }
    }

    private String getUserEmailFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        return preferences.getString("username", "");
    }

    private String getUserPasswordFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        return preferences.getString("password", "");
    }

    private void startBookingActivity() {
        Intent intent = new Intent(this, BookingActivity.class);
        startActivity(intent);
    }

    private void startSignupActivity() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
