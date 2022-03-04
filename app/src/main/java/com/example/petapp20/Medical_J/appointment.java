package com.example.petapp20.Medical_J;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petapp20.R;
import com.example.petapp20.appointment_form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class appointment extends AppCompatActivity implements View.OnClickListener {

    CalendarView calendarView2;
    TextView editTextDate, editTextTime2;
    int t2Hour,t2Minute;
    private String UID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        findViewById(R.id.button2).setOnClickListener(this);
        calendarView2 = findViewById(R.id.calendarView2);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime2 = findViewById(R.id.editTextTime2);
        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //set Date for appointment
        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                editTextDate.setText(date);

            }
        });

        //set time for appointment
        editTextTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(appointment.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                t2Hour = hourOfDay;
                                t2Minute = minute;
                                //store hour and minute in string
                                String time = t2Hour + ":" + t2Minute;
                                //Initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");
                                    editTextTime2.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0,false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t2Hour, t2Minute);
                timePickerDialog.show();
            }
        });
    }

    public void Appointment_sign(){
        final String date = editTextDate.getText().toString().trim();
        final String time = editTextTime2.getText().toString().trim();

        appointment_form AF = new appointment_form(date,time);
        FirebaseDatabase.getInstance().getReference("Clients").child(UID).child("appointment").setValue(AF)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(appointment.this, "Appointment successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(appointment.this, Medical.class);
                        startActivity(i);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                Appointment_sign();
                break;
        }
    }
    
}