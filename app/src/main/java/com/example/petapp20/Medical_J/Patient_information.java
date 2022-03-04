package com.example.petapp20.Medical_J;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petapp20.R;
import com.example.petapp20.Users_Form;
import com.example.petapp20.appointment_form;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Patient_information extends AppCompatActivity {

    ImageView patient_return_btn;
    TextView Patient_appointment;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_information);

        patient_return_btn = findViewById(R.id.patient_return_btn);
        Patient_appointment = findViewById(R.id.Patient_appointment);

        databaseReference = FirebaseDatabase.getInstance().getReference("Clients");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users_Form curr_user = snapshot.child(user.getUid()).getValue(Users_Form.class);
                appointment_form curr_add = curr_user.getAppointment();
                Patient_appointment.setText("Your appointment date is: " + curr_add.getDate() + "\n"
                        + "Your appointment time is: " + curr_add.getTime());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Return to Home
        patient_return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Patient_information.this, Medical.class));
            }
        });
    }
}