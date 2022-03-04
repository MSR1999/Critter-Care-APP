package com.example.petapp20.Medical_J;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petapp20.Home;
import com.example.petapp20.R;
import com.example.petapp20.Users_Form;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Medical extends AppCompatActivity {


    ImageView appointment, hospital, diagnosis, doctor, treatment, prescription, patient_info, medical_return_btn;
    private Users_Form u;
    private DatabaseReference ref_users; //the reference for Users real time database
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();//the current online user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediacl);


        medical_return_btn = findViewById(R.id.medical_return_btn);
        appointment = findViewById(R.id.appointment);
        hospital = findViewById(R.id.hospital);
        diagnosis = findViewById(R.id.diagnosis);
        doctor = findViewById(R.id.doctor);
        treatment = findViewById(R.id.treatment);
        prescription = findViewById(R.id.prescription);
        patient_info = findViewById(R.id.patient_info);

        //        Jump to the log in interface
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Medical.this, appointment.class));
            }
        });

        //        Jump to the log in interface
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Medical.this, hospital.class));
            }
        });

        //        Jump to the log in interface
        diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Medical.this, diagnosis.class));
            }
        });

        //        Jump to the log in interface
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Medical.this, doctor.class));
            }
        });

        //        Jump to the log in interface
        treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Medical.this, treatment.class));
            }
        });

        //        Jump to the log in interface
        prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Medical.this, prescription.class));
            }
        });

        //        Jump to the patient information interface
        patient_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Medical.this, Patient_information.class));
            }
        });


        //Return to Home
        medical_return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Medical.this, Home.class));
            }
        });


        ref_users = FirebaseDatabase.getInstance().getReference("Clients"); //get reference to Users

        ref_users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                u = dataSnapshot.child(user.getUid()).getValue(Users_Form.class); // get user id of the current user
                TextView Hello_Name = findViewById(R.id.Welcome_back);
                Hello_Name.setText( u.getFirstName() + " Welcome Back!");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}