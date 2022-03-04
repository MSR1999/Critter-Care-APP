package com.example.petapp20.edit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petapp20.MainActivity;
import com.example.petapp20.Profile;
import com.example.petapp20.R;
import com.example.petapp20.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_firstname extends AppCompatActivity implements View.OnClickListener {

    private EditText first_name;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //the current user in the database
    TextView Firstname_Cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_firstname);

        findViewById(R.id.Firstname_done).setOnClickListener(this);
        first_name = findViewById(R.id.Edit_Firstname);
        Firstname_Cancel = findViewById(R.id.Firstname_Cancel);

        //Return to profile interface
        Firstname_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Edit_firstname.this, Profile.class));
            }
        });
    }




    private void changeName() {
        String F_name = first_name.getText().toString().trim();

        if (F_name.isEmpty()) {
            first_name.setError("Empty name input");
            first_name.requestFocus();
            return;
        }


        FirebaseDatabase.getInstance().getReference("Clients").child(user.getUid()).child("firstName").setValue(F_name)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Edit_firstname.this, "Edit successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Edit_firstname.this, Profile.class);
                        startActivity(i);
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Firstname_done:
                changeName();
                break;
        }
    }
}