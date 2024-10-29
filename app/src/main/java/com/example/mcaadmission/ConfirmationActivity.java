package com.example.mcaadmission;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {

    TextView confirmationDetails;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        confirmationDetails = findViewById(R.id.confirmationDetails);
        ratingBar = findViewById(R.id.ratingBar);

        // Retrieve data from intent
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String email = getIntent().getStringExtra("email");
        String date = getIntent().getStringExtra("date");
        boolean hostel = getIntent().getBooleanExtra("hostel", false);

        // Display confirmation details
        String hostelStatus = hostel ? "Yes" : "No";
        String details = "Name: " + name + "\n" +
                "Phone: " + phone + "\n" +
                "Email: " + email + "\n" +
                "Selected Date: " + date + "\n" +
                "Hostel Required: " + hostelStatus;

        confirmationDetails.setText(details);

        // Optional: Handle rating events
        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            // Handle rating event if needed
        });
    }
}
