package com.example.petapp20.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petapp20.Address_form;
import com.example.petapp20.Profile;
import com.example.petapp20.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_Address extends AppCompatActivity implements View.OnClickListener {

    private EditText City;
    private EditText Street;
    private EditText House_num;
    private EditText Phone_num;
    private String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        findViewById(R.id.Address_done).setOnClickListener(this);
        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        City = findViewById(R.id.Address_City);
        Street = findViewById(R.id.Address_Street);
        House_num = findViewById(R.id.Address_house_num);
        Phone_num = findViewById(R.id.Address_phone_num);

    }

        public void Address_sign() {
            final String city = City.getText().toString().trim();
            final String street = Street.getText().toString().trim();
            final String house = House_num.getText().toString().trim();
            final String phone = Phone_num.getText().toString().trim();

            if (city.isEmpty()) {
                City.setError("No City given");
                City.requestFocus();
                return;
            }
            if (street.isEmpty()) {
                Street.setError("No Street given");
                Street.requestFocus();
                return;
            }
            if (house.isEmpty()) {
                House_num.setError("No House number given");
                House_num.requestFocus();
                return;
            }
            if (phone.isEmpty()) {
                Phone_num.setError("No Phone number given");
                Phone_num.requestFocus();
                return;
            }
            if (phone.matches("[0-9]+") == false && phone.length() < 8) {
                Phone_num.setError("phone provided is not valid!");
                Phone_num.requestFocus();
                return;
            }

            Address_form AF = new Address_form(city, street, house, phone);
            FirebaseDatabase.getInstance().getReference("Clients").child(UID).child("address").setValue(AF)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(Edit_Address.this, "Edit successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Edit_Address.this, Profile.class);
                            startActivity(i);
                        }
                    });
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Address_done:
                    Address_sign();
                    break;
            }
        }
}