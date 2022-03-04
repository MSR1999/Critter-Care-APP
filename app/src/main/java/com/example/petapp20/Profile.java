package com.example.petapp20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petapp20.edit.Edit_Address;
import com.example.petapp20.edit.Edit_firstname;
import com.example.petapp20.edit.Edit_lastname;
import com.example.petapp20.edit.Edit_password;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    Button Logout;
    ImageView Return_btn, Camera;
    private TextView edit_fn;
    private TextView edit_ln;
    private TextView edit_email;
    private TextView edit_pass;
    private TextView edit_address;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Return_btn = findViewById(R.id.profile_return_btn);
        Logout = findViewById(R.id.Profile_logout);
        Return_btn = findViewById(R.id.profile_return_btn);
        edit_address = findViewById(R.id.Profile_address);
        edit_pass = findViewById(R.id.Profile_Pass);
        edit_email = findViewById(R.id.Profile_email);
        edit_ln = findViewById(R.id.Profile_lastname);
        edit_fn = findViewById(R.id.Profile_firstname);
        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Clients");

        //get the current user data
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users_Form curr_user = snapshot.child(user.getUid()).getValue(Users_Form.class);
                Address_form curr_add = curr_user.getAddress();
                edit_fn.setText(curr_user.getFirstName());
                edit_ln.setText(curr_user.getLastName());
                edit_email.setText(curr_user.getEmail());
                edit_pass.setText(curr_user.getPassword());
                edit_address.setText("address: city: " + curr_add.getCity() + " street: " + curr_add.getStreet() + " house number: " + curr_add.getHouse_num()
                        + " phone number: " + curr_add.getPhone_num());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Log out
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i_out = new Intent(Profile.this, MainActivity.class);
                startActivity(i_out);
            }
        });

        //EDIT ADDRESS BUTTON
        edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_EA = new Intent(Profile.this, Edit_Address.class);
                startActivity(i_EA);
            }
        });

        //EDIT FIRST NAME BUTTON
        edit_fn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_EF = new Intent(Profile.this, Edit_firstname.class);
                startActivity(i_EF);
            }
        });

        //EDIT LAST NAME BUTTON
        edit_ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_EF = new Intent(Profile.this, Edit_lastname.class);
                startActivity(i_EF);
            }
        });

        //EDIT PASSWORD BUTTON
        edit_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_EP = new Intent(Profile.this, Edit_password.class);
                startActivity(i_EP);
            }
        });

        //Return to Home
        Return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, Home.class));
            }
        });
    }
}

