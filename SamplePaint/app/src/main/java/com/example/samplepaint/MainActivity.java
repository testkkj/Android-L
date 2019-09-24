package com.example.samplepaint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BestPaintBoard bestPaintBoard = new BestPaintBoard(this);
        setContentView(bestPaintBoard);
    }
}
