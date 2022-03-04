package com.example.petapp20;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Adopt_result extends AppCompatActivity {

    TextView Adopt_secondText;
    WebView Adopt_webSiteView;
    Button Adopt_webButton;
    String A;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_result);

        Adopt_secondText = findViewById(R.id.Adopt_secondText);
        Adopt_webSiteView = findViewById(R.id.Adopt_webSiteView);
        Adopt_webButton = findViewById(R.id.Adopt_webButton);

        Bundle bundle = getIntent().getExtras();
        A = bundle.getString("tag");
        Adopt_secondText.setText(A);

        Adopt_webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.google.com/search?q="+A;

                Adopt_webSiteView.getSettings().setJavaScriptEnabled(true);
                Adopt_webSiteView.setWebViewClient(new WebViewClient());

                Adopt_webSiteView.loadUrl(url);
            }
        });
    }
}