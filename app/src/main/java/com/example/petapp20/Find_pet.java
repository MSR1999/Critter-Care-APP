package com.example.petapp20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Find_pet extends AppCompatActivity {

    Button submit;
    EditText textSearch;
    String passData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pet);

        submit = (Button) findViewById(R.id.submit);
        textSearch = (EditText) findViewById(R.id.searchBox);





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //
                passData = textSearch.getText().toString();
                Intent intent = new Intent(Find_pet.this, Find_search.class);
                intent.putExtra("tag",passData);
                startActivity(intent);
            }
        });
    }
}