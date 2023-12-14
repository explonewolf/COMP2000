// TimeActivity.java
package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class TimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_layout);

        final TimePicker timePicker = findViewById(R.id.timePicker);
        Button bookButton = findViewById(R.id.buttonBookTime);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected time from the TimePicker
                String selectedTime = getTimeFromTimePicker(timePicker);

                // You can use the selected time as needed
                showToast("Time selected: " + selectedTime);

                // If needed, you can add further logic or navigate to another activity here
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String getTimeFromTimePicker(TimePicker timePicker) {
        // Note: In Android API 23 (Android 6.0) and later, TimePicker uses 24-hour format by default
        // If you want to handle 12-hour format, you may need additional logic
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        return String.format("%02d:%02d", hour, minute);
    }
}
