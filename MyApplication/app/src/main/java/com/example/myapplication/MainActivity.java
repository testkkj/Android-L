package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v){
        Toast.makeText(this, "메시지의 내용이다.", Toast.LENGTH_LONG).show();
    }

    public void onButton2Click(View v){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(myIntent);
    }

    public void onButton3Click(View v){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-0000-1111"));
        startActivity(myIntent);
    }

    public void onButton4Click(View v){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:순천오병원"));
        startActivity(myIntent);
    }
}