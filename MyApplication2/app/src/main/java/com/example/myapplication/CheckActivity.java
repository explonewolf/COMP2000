// CheckActivity.java
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CheckActivity extends AppCompatActivity {
    private String formatTime(int hour, int minute) {
        return String.format("%02d:%02d", hour, minute);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_layout);

        TextView bookingTextView = findViewById(R.id.textView);



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


            // Now you can use these values to display in your TextView
            String bookingMessage = getString(R.string.booking_message, String.valueOf(noAdults), String.valueOf(noChildren), formatTime(hour, minute), date, "oceania");
            bookingTextView.setText(bookingMessage);


        }
    }
}
