package com.example.challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();

    LinearLayout linearLayout;
    LinearLayout linearLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class MyThread extends Thread {
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    linearLayout = findViewById(R.id.client);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
