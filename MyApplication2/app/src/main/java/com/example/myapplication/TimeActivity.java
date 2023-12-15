// TimeActivity.java
package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeActivity extends AppCompatActivity {
    private CheckBox checkBoxInside;
    private CheckBox checkBoxOutside;
    int tableSize;
    public String datep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_layout);

        final TimePicker timePicker = findViewById(R.id.timePicker);
        Button bookButton = findViewById(R.id.buttonBookTime);

        // Initialize CheckBox views
        checkBoxInside = findViewById(R.id.checkBoxInside);
        checkBoxOutside = findViewById(R.id.checkBoxOutside);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedTime = getTimeFromTimePicker(timePicker);
                String seatingPreference = getSeatingPreference();
                // Make network request to the API
                sendReservationToApi(selectedTime, seatingPreference);
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
        tableSize = noAdults + noChildren;
        datep = date;
        Intent intent = new Intent(TimeActivity.this, CheckActivity.class);
        intent.putExtra("hour", hour);
        intent.putExtra("minute", minute);
        intent.putExtra("noAdults", noAdults);
        intent.putExtra("noChildren", noChildren);
        intent.putExtra("date", date);
        intent.putExtra("seatingPreference", getSeatingPreference()); // Add seating preference
        startActivity(intent);
        return String.format("%02d:%02d", hour, minute);
    }

    private String getSeatingPreference() {
        // Determine the selected seating preference based on checkbox state
        if (checkBoxInside.isChecked() && checkBoxOutside.isChecked()) {
            return "Inside and Outside";
        } else if (checkBoxInside.isChecked()) {
            return "Inside";
        } else if (checkBoxOutside.isChecked()) {
            return "Outside";
        } else {
            return "No preference";
        }
    }

    private void sendReservationToApi(String selectedTime, String seatingPreference) {
        // Instantiate the RequestQueue.
        String meal;
        if (isBetween(selectedTime, "00:01", "12:00")) {
            meal = "Breakfast";
        } else if (isBetween(selectedTime, "12:01", "18:00")) {
            meal = "Lunch";
        } else {
            meal = "Dinner";
        }
        Volley.newRequestQueue(this);

        String url = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations";

        // Create the JSON object for the request
        JSONObject reservationObject = new JSONObject();
        try {
            reservationObject.put("customerName", getNameFromSharedPreferences());
            reservationObject.put("customerPhoneNumber", getPhoneFromSharedPreferences());
            reservationObject.put("meal", meal);
            reservationObject.put("seatingArea", seatingPreference);
            reservationObject.put("tableSize", tableSize);
            reservationObject.put("date", formatDateForServer(datep)); // Use the formatted date
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create the request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                reservationObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        showToast("Reservation successful!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showToast("Error making reservation");
                    }
                });

        // Add the request to the RequestQueue.
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    private String getNameFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        return preferences.getString("name", "");
    }

    private String getPhoneFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        return preferences.getString("phoneNumber", "");
    }

    private boolean isBetween(String target, String startTime, String endTime) {
        return target.compareTo(startTime) >= 0 && target.compareTo(endTime) <= 0;
    }

    private String formatDateForServer(String inputDate) {
        try {
            // Parse the input date
            SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            Date date = inputFormat.parse(inputDate);

            // Format the date to yyyy-MM-dd
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return inputDate; // Return the original date in case of an error
        }
    }
}
