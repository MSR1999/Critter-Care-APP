package com.example.petapp20.Order;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petapp20.Home;
import com.example.petapp20.R;

public class pay_by_credit_card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_by_credit_card);
        //SET THE WINDOW MATRIX TO BE POP-UP SIZE
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        Button pay = findViewById(R.id.pay_btn_CC);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pay_by_credit_card.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
