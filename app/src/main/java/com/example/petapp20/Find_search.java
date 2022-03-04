package com.example.petapp20;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Find_search extends AppCompatActivity {

    TextView text;
    WebView webSite;
    Button webButton;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_search);

        text = (TextView) findViewById(R.id.secondText);
        webSite = (WebView) findViewById(R.id.webSiteView);
        webButton = (Button) findViewById(R.id.webButton);

        Bundle bundle = getIntent().getExtras();
        s = bundle.getString("tag");
        text.setText(s);

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://www.google.com/search?q="+s;

                webSite.getSettings().setJavaScriptEnabled(true);
                webSite.setWebViewClient(new WebViewClient());

                webSite.loadUrl(url);
                //

            }
        });
    }
}