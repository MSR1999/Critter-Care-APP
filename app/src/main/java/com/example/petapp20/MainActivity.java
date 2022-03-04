package com.example.petapp20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView Create_new_Account;
    EditText Login_Email, Login_Password;
    Button Login_btn;
    String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Create_new_Account = findViewById(R.id.Create_Account);
        Login_Email = findViewById(R.id.Login_Email);
        Login_btn = findViewById(R.id.Login_btn);
        Login_Password = findViewById(R.id.Login_Password);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

//        Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


//        Jump to the sign up interface
        Create_new_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

//        Set the click event of the Login button
        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforLogin();
            }
        });

    }

    //    Determine whether the mail and password is correct
    private void perforLogin() {
        String email = Login_Email.getText().toString();
        String password = Login_Password.getText().toString();

        if (!email.matches(emailPattern)) {
            Login_Email.setError("Enter Correct Email");

        } else if (password.isEmpty() || password.length() < 6) {
            Login_Password.setError("Enter Proper Password");

        } else {
          logIn(email,password);
        }
    }
    void logIn(String email, String password){
        progressDialog.setMessage("Please Wait While Login...");
        progressDialog.setTitle("Login");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    progressDialog.dismiss();
                    sendUserToNextActivity();
                    Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    //    Login successful jump event
    private void sendUserToNextActivity() {
        Intent intent=new Intent(MainActivity.this,Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
