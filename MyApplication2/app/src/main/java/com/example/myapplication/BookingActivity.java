// BookingActivity.java
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    public String numAdults;
    public String numKids;
    public String date ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_layout);

        final DatePicker datePicker = findViewById(R.id.datePicker);
        final EditText adultsEditText = findViewById(R.id.editTextAdults);
        final EditText kidsEditText = findViewById(R.id.editTextKids);
        Button bookButton = findViewById(R.id.buttonBookNow);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get values from the UI
                String selectedDate = getDateFromDatePicker(datePicker);
                int numAdults = Integer.parseInt(adultsEditText.getText().toString());
                int numKids = Integer.parseInt(kidsEditText.getText().toString());

                // Check if the total number of attendees is within the limit (10)
                if (numAdults + numKids <= 10) {
                    showToast("Booking successful for " + selectedDate + "\nAdults: " + numAdults + "\nKids: " + numKids);


                    Intent intent = new Intent(BookingActivity.this, TimeActivity.class);
                    intent.putExtra("noAdults", numAdults);
                    intent.putExtra("noChildren", numKids);
                    intent.putExtra("date", selectedDate);
                    startActivity(intent);
                } else {
                    showToast("Exceeded maximum limit of 10 attendees.");
                }
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public String getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();

        return String.format("%02d/%02d/%04d", month, day, year);
    }
}
