package com.example.mcaadmission;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText nameInput, phoneInput, emailInput;
    Switch hostelSwitch;
    Button dateButton, switchImageButton, submitButton;
    ImageView imageView;
    String selectedDate = "";
    int currentImageIndex = 0;
    int[] images = {R.drawable.img, R.drawable.img_1, R.drawable.img_2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        nameInput = findViewById(R.id.editTextName);
        phoneInput = findViewById(R.id.editTextPhone);
        emailInput = findViewById(R.id.editTextEmail);
        hostelSwitch = findViewById(R.id.hostelSwitch);
        dateButton = findViewById(R.id.dateButton);
        switchImageButton = findViewById(R.id.switchImageButton);
        submitButton = findViewById(R.id.submitButton);
        imageView = findViewById(R.id.imageView);

        // Set initial image
        imageView.setImageResource(images[currentImageIndex]);

        // Date Picker Dialog
        dateButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        dateButton.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });

        // Image Switch Button
        switchImageButton.setOnClickListener(v -> {
            currentImageIndex = (currentImageIndex + 1) % images.length;
            imageView.setImageResource(images[currentImageIndex]);
        });

        // Submit Button: Pass data to ConfirmationActivity
        submitButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String phone = phoneInput.getText().toString();
            String email = emailInput.getText().toString();
            boolean wantsHostel = hostelSwitch.isChecked();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || selectedDate.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                intent.putExtra("date", selectedDate);
                intent.putExtra("hostel", wantsHostel);
                startActivity(intent);
            }
        });
    }
}
