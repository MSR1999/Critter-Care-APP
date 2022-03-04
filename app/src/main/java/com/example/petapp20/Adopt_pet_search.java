package com.example.petapp20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Adopt_pet_search extends AppCompatActivity {

    Button Adopt_submit;
    EditText Adopt_searchBox;
    String Adopt_passData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_pet_search);

        Adopt_submit = findViewById(R.id.Adopt_submit);
        Adopt_searchBox = findViewById(R.id.Adopt_searchBox);


        Adopt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Adopt_passData = Adopt_searchBox.getText().toString();
                Intent intent = new Intent(Adopt_pet_search.this, Adopt_result.class);
                intent.putExtra("tag",Adopt_passData);
                startActivity(intent);

            }
        });
    }
}