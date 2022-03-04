package com.example.petapp20.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petapp20.Profile;
import com.example.petapp20.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_lastname extends AppCompatActivity implements View.OnClickListener {

    private EditText last_name;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //the current user in the database
    TextView Lastname_Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        findViewById(R.id.Lastname_done).setOnClickListener(this);
        last_name = findViewById(R.id.Edit_Lastname);
        Lastname_Cancel = findViewById(R.id.Lastname_Cancel);

        //Return to profile interface
        Lastname_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Edit_lastname.this, Profile.class));
            }
        });
    }




    private void changeName() {
        String F_name = last_name.getText().toString().trim();

        if (F_name.isEmpty()) {
            last_name.setError("Empty name input");
            last_name.requestFocus();
            return;
        }


        FirebaseDatabase.getInstance().getReference("Clients").child(user.getUid()).child("lastName").setValue(F_name)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Edit_lastname.this, "Edit successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Edit_lastname.this, Profile.class);
                        startActivity(i);
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Lastname_done:
                changeName();
                break;
        }
    }
}