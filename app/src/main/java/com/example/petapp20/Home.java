package com.example.petapp20;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.petapp20.Medical_J.Medical;
import com.example.petapp20.fragment.Adopt_a_pet_Fragment;
import com.example.petapp20.fragment.Find_a_pet_Fragment;
import com.example.petapp20.fragment.MedicalFragment;
import com.example.petapp20.Order.Order_Fragment;
import com.example.petapp20.Order.Pet_care_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    ImageView personal_btn, medical_btn;

    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView=findViewById(R.id.Bottom_Navigate);
        personal_btn=findViewById(R.id.personal_ic);
        medical_btn = findViewById(R.id.medical_btn);

        //Jump to profile
        personal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Profile.class));
            }
        });

        //Jump to medical
        medical_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Medical.class));
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new MedicalFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){

                    Fragment fragment=null;

                    switch (menuItem.getItemId())
                    {
                        case R.id.Home:
                            fragment=new MedicalFragment();
                            break;
                        case R.id.adopt:
                            fragment=new Adopt_a_pet_Fragment();
                            break;
                        case R.id.care:
                            fragment=new Pet_care_Fragment();
                            break;
                        case R.id.find:
                            fragment=new Find_a_pet_Fragment();
                            break;
                        case R.id.order:
                            fragment=new Order_Fragment();
                            break;


                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();

                    return true;
                }
            };

}