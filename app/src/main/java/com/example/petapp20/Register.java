package com.example.petapp20;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    TextView Already_Have_Account;
    EditText Signup_Email, Signup_Password, Signup_ConformPassword, First_name, Last_name;
    String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Already_Have_Account = findViewById(R.id.Already_Have_Account);
        findViewById(R.id.Signup_btn).setOnClickListener(this);
        Signup_Email = findViewById(R.id.Sign_Email);
        Signup_Password = findViewById(R.id.Sign_Password);
        Signup_ConformPassword = findViewById(R.id.Sign_RePassword);
        First_name = findViewById(R.id.Sign_First);
        Last_name = findViewById(R.id.Sign_Last);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

//        Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        Jump to the log in interface
        Already_Have_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    // Register user
    private void RegisterUser(){

        final String Email=Signup_Email.getText().toString().trim();
        final String Password=Signup_Password.getText().toString().trim();
        final String ConformPassword=Signup_ConformPassword.getText().toString().trim();
        final String First=First_name.getText().toString().trim();
        final String Last=Last_name.getText().toString().trim();

        //Determine typr information
        if (!Email.matches(emailPattern)) {
            Signup_Email.setError("Enter Correct Email");
            Signup_Email.requestFocus();
            return;

        }
        if (Password.isEmpty() || Password.length()<6) {
            Signup_Password.setError("Enter Proper Password");
            Signup_Password.requestFocus();
            return;
        }
        if (First.isEmpty()) {
            First_name.setError("First Name Required");
            First_name.requestFocus();
            return;
        }
        if (Last.isEmpty()) {
            Last_name.setError("Last Name Required");
            Last_name.requestFocus();
            return;
        }
        if (!Password.equals(ConformPassword)) {
            Signup_ConformPassword.setError("Password Not Match Both Field");
            Signup_ConformPassword.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //we will store the additional filled in fire base database
                    Users_Form user = new Users_Form(First, Email, Last, Password);
                    FirebaseDatabase.getInstance().getReference("Clients").child(FirebaseAuth.getInstance().getUid()).setValue(user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(Register.this, Home.class);
                                            startActivity(i);
                                        } else {
                                            Toast.makeText(Register.this, "Error, couldn't sign up", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
            } else {
                    Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Signup_btn:
                RegisterUser();
                break;
        }
    }
}
