package com.example.samplecustomviewdrawable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomViewDrawable customViewDrawable = new CustomViewDrawable(this);
        setContentView(customViewDrawable);
    }
}
