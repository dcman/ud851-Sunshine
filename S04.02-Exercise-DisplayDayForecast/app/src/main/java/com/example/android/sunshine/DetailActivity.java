package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private TextView mWeatherData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWeatherData = (TextView) findViewById(R.id.detail_tv);
        if (getIntent().hasExtra(Intent.EXTRA_TEXT)){
            mWeatherData.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
        }
    }
}