package com.example.samplecustomviewimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomViewImage customViewImage = new CustomViewImage(this);
        setContentView(customViewImage);
    }
}
