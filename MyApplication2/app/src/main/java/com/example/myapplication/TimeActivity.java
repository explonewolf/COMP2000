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
public String minute;
public String hour;
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

    public String getTimeFromTimePicker(TimePicker timePicker) {
        Bundle extras = getIntent().getExtras();
        int noAdults = extras.getInt("noAdults", 0);
        int noChildren = extras.getInt("noChildren", 0);
        String date = extras.getString("date", "");
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        Intent intent = new Intent(TimeActivity.this, CheckActivity.class);
        intent.putExtra("hour", hour);
        intent.putExtra("minute", minute);
        intent.putExtra("noAdults", noAdults);
        intent.putExtra("noChildren", noChildren);
        intent.putExtra("date", date);
        startActivity(intent);
        return String.format("%02d:%02d", hour, minute);

    }
}
