// SignupActivity.java
package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class SignupActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "user_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        Button signUpButton = findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    public void signUp() {
        EditText nameEditText = findViewById(R.id.editTextName);
        EditText usernameEditText = findViewById(R.id.editTextUsername);
        EditText phoneNumberEditText = findViewById(R.id.editTextPhoneNumber);
        EditText passwordEditText = findViewById(R.id.editTextPassword);

        String name = nameEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        String password = passwordEditText.getText().toString();


        // Create a User object with the provided information
        User user = new User(name, username, phoneNumber, password);

        // Save the user to SharedPreferences
        saveUserToSharedPreferences(user);

        String message = "Sign Up Successful!\nUUID: " + user.getUuid();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        startMainActivity();
    }

    private void saveUserToSharedPreferences(User user) {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("name", user.getName());
        editor.putString("username", user.getUsername());
        editor.putString("phoneNumber", user.getPhoneNumber());
        editor.putString("password", user.getPassword());
        editor.putString("uuid", user.getUuid());

        editor.apply();
    }

    private static class User {
        private String name;
        private String username;
        private String phoneNumber;
        private String password;
        private String uuid;

        public User(String name, String username, String phoneNumber, String password) {
            this.name = name;
            this.username = username;
            this.phoneNumber = phoneNumber;
            this.password = password;
            this.uuid = UUID.randomUUID().toString();
        }

        public String getName() {
            return name;
        }

        public String getUsername() {
            return username;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getPassword() {
            return password;
        }

        public String getUuid() {
            return uuid;
        }

    }
    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
