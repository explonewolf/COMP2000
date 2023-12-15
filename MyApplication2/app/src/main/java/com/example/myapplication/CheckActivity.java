// CheckActivity.java
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CheckActivity extends AppCompatActivity {

    private String formatTime(int hour, int minute) {
        return String.format("%02d:%02d", hour, minute);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_layout);

        TextView bookingTextView = findViewById(R.id.textView);
        TextView codeTextView = findViewById(R.id.textViewCode);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            // Retrieve information from the intent
            int noAdults = extras.getInt("noAdults", 0);
            int noChildren = extras.getInt("noChildren", 0);
            String date = extras.getString("date", "");
            int hour = extras.getInt("hour", 0);
            int minute = extras.getInt("minute", 0);

            Log.d("CheckActivity", "noAdults: " + noAdults);
            Log.d("CheckActivity", "noChildren: " + noChildren);
            Log.d("CheckActivity", "date: " + date);
            Log.d("CheckActivity", "hour: " + hour);
            Log.d("CheckActivity", "minute: " + minute);

            // display in your TextView
            String bookingMessage = getString(R.string.booking_message, String.valueOf(noAdults), String.valueOf(noChildren), formatTime(hour, minute), date, "oceania");
            bookingTextView.setText(bookingMessage);

            // Generate and display a code
            int randomCode = generateRandomCode();
            codeTextView.setText("Booking Code: " + randomCode);
        }

        // Add click listener for the "Cancel" button
        Button cancelButton = findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle cancel button click
                cancelBooking();
            }
        });
    }

    // Method to handle "Cancel" button click
    public void cancelBooking() {
        // Return to BookingActivity
        Intent intent = new Intent(this, BookingActivity.class);
        startActivity(intent);
        finish(); // finish the current activity
    }

    // Method to generate a random code
    private int generateRandomCode() {
        Random random = new Random();
        return random.nextInt(10000);
    }
}
