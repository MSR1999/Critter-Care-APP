package com.example.petapp20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class introductory extends AppCompatActivity {
    ImageView logo,appName,backGround;
    LottieAnimationView lottieAnimationView;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);
        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.name);
        backGround = findViewById(R.id.img);
        lottieAnimationView = findViewById(R.id.lottoe);

        backGround.animate().translationY(-2200).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(1600).setDuration(1000).setStartDelay(4000);
        appName.animate().translationY(1600).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1600).setDuration(1000).setStartDelay(4000);

        //autoAutomatically Change Activity
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(introductory.this, MainActivity.class);
                startActivities(new Intent[]{intent});
                finish();

            }
        },6000);
    }


}