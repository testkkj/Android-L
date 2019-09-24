package com.example.samplecustomviewstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomViewStyle customViewStyle = new CustomViewStyle(this);
        setContentView(customViewStyle);
    }
}
