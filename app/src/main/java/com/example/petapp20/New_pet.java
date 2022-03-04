package com.example.petapp20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class New_pet extends AppCompatActivity implements View.OnClickListener{

    private EditText Name;
    private EditText Breed;
    private EditText Gender;
    private EditText Color;
    private EditText Age;
    private String UID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);

        findViewById(R.id.BTN_Save).setOnClickListener(this);
        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Name = findViewById(R.id.Pet_name);
        Breed = findViewById(R.id.Pet_breed);
        Gender = findViewById(R.id.Pet_gender);
        Color = findViewById(R.id.Pet_color);
        Age = findViewById(R.id.Pet_ages);

    }


    private void changePet(){
        String P_Name = Name.getText().toString().trim();
        String P_Breed = Breed.getText().toString().trim();
        String P_Gender = Gender.getText().toString().trim();
        String P_Color = Color.getText().toString().trim();
        String P_Age = Age.getText().toString().trim();

        if (P_Name.isEmpty()) {
            Name.setError("Empty name input");
            Name.requestFocus();
            return;
        }
        if (P_Breed.isEmpty()) {
            Breed.setError("Empty name input");
            Breed.requestFocus();
            return;
        }
        if (P_Gender.isEmpty()) {
            Gender.setError("Empty name input");
            Gender.requestFocus();
            return;
        }
        if (P_Color.isEmpty()) {
            Color.setError("Empty name input");
            Color.requestFocus();
            return;
        }
        if (P_Age.isEmpty()) {
            Age.setError("Empty name input");
            Age.requestFocus();
            return;
        }

        Pet_form PF = new Pet_form(P_Name, P_Breed, P_Gender, P_Color, P_Age);
        FirebaseDatabase.getInstance().getReference("Clients").child(UID).child("pet").setValue(PF)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(New_pet.this, "Save successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(New_pet.this, Post_pet.class);
                        startActivity(i);
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BTN_Save:
                changePet();
                break;
        }
    }
}