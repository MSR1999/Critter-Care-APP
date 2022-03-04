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
import com.example.petapp20.Users_Form;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Edit_password extends AppCompatActivity implements View.OnClickListener {

    private EditText current_password;
    private EditText new_password;
    private Users_Form u;
    private DatabaseReference ref_users;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //the current user in the database
    TextView Password_Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        Password_Cancel = findViewById(R.id.Password_Cancel);


        //Return to profile interface
        Password_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Edit_password.this, Profile.class));
            }
        });


        ref_users = FirebaseDatabase.getInstance().getReference("Clients"); //get database Users reference
        ref_users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                u = snapshot.child(user.getUid()).getValue(Users_Form.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Edit_password.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });


        current_password = (EditText) findViewById(R.id.Current_Password);
        new_password = (EditText) findViewById(R.id.New_Password);
    }




    private void change_password() {
        String password = current_password.getText().toString().trim();
        String new_user_pass = new_password.getText().toString().trim();
        if(password.isEmpty()){
            current_password.setError("current password is empty");
            current_password.requestFocus();
            return;
        }
        if(!password.equals(u.getPassword())){
            current_password.setError("wrong password");
            current_password.requestFocus();
            return;
        }
        if(new_user_pass.isEmpty()){
            new_password.setError("new password is empty");
            new_password.requestFocus();
            return;
        }
        if (new_user_pass.equals(password)){
            new_password.setError("the new password is the same as the old one");
            new_password.requestFocus();
            return;
        }
        user.updatePassword(new_user_pass);
        ref_users.child(user.getUid()).child("password").setValue(new_user_pass);
        Toast.makeText(Edit_password.this, "Edit successful", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Edit_password.this, Profile.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Password_done:
                change_password();
                break;
        }
    }
}