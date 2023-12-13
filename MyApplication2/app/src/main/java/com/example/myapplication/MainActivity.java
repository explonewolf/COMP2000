package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
            finish();
        } else {
            showToast("Invalid credentials. Try again.");

        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public class SecondActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_second);
            // Additional initialization or logic for the second activity
        }
    }
}
