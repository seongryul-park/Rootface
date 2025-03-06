package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.HashMap;
import java.util.Map;

public class HealthActivity extends AppCompatActivity {

    private Map<ImageButton, String> buttonUrls;

    private Button backButton;
    private ImageButton armButton, legButton, waistButton, neckButton, shoulderButton, ankleButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        backButton = findViewById(R.id.buttonback);
        armButton = findViewById(R.id.arm);
        legButton = findViewById(R.id.leg);
        waistButton = findViewById(R.id.waist);
        neckButton = findViewById(R.id.neck);
        shoulderButton = findViewById(R.id.shoulder);
        ankleButton = findViewById(R.id.ankle);

        setupButtonUrls();
        setupButtonListeners();
    }

    private void setupButtonUrls() {
        buttonUrls = new HashMap<ImageButton, String>();
        buttonUrls.put(armButton, "https://lifestyle.fit/ko/%ED%9B%88%EB%A0%A8/%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%B9%AD/%ED%9B%88%EB%A0%A8-%ED%8C%94%EC%9D%84-%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%B9%AD%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95/");

        buttonUrls.put(legButton, "https://lifestyle.fit/ko/%ED%9B%88%EB%A0%A8/%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%B9%AD/%EC%A0%84%EB%B0%A9%EC%8B%AD%EC%9E%90%EC%9D%B8%EB%8C%80-%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%B9%AD/");

        buttonUrls.put(waistButton, "https://lifestyle.fit/ko/%ED%9B%88%EB%A0%A8/%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%B9%AD/%ED%97%88%EB%A6%AC%ED%86%B5%EC%A6%9D%EC%9D%84-%EC%98%88%EB%B0%A9%ED%95%98%EB%8A%94-%EC%9A%B4%EB%8F%99/");

        buttonUrls.put(neckButton, "https://lifestyle.fit/ko/%ED%9B%88%EB%A0%A8/%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%B9%AD/%EB%AA%A9-%EA%B5%AC%EC%B6%95%EC%9D%84-%EC%98%88%EB%B0%A9%ED%95%98%EA%B8%B0-%EC%9C%84%ED%95%9C-%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%B9%AD-%EC%9A%B4%EB%8F%99/");

        buttonUrls.put(shoulderButton, "https://lifestyle.fit/ko/%ED%9B%88%EB%A0%A8/%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%B9%AD/%EC%8A%B9%EB%AA%A8%EA%B7%BC-%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%B9%AD/");

        buttonUrls.put(ankleButton, "https://lifestyle.fit/ko/%ED%9B%88%EB%A0%A8/%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%B9%AD/%EC%86%90%EB%AA%A9-%EA%B1%B4%EC%97%BC-%EC%9A%B4%EB%8F%99/");
    }

    private void setupButtonListeners() {
        backButton.setOnClickListener(v -> finish());

        armButton.setOnClickListener(v -> openUrl(buttonUrls.get(armButton)));
        legButton.setOnClickListener(v -> openUrl(buttonUrls.get(legButton)));
        waistButton.setOnClickListener(v -> openUrl(buttonUrls.get(waistButton)));
        neckButton.setOnClickListener(v -> openUrl(buttonUrls.get(neckButton)));
        shoulderButton.setOnClickListener(v -> openUrl(buttonUrls.get(shoulderButton)));
        ankleButton.setOnClickListener(v -> openUrl(buttonUrls.get(ankleButton)));
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}