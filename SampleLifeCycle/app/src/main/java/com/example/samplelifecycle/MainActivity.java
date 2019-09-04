package com.example.samplelifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        println("onCreate");

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        println("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        println("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        println("onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        println("onPause");
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        println("onResume");
        restoreSate();
    }

    public void println(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        Log.d("Main", s);
    }

    protected void restoreSate() {
        SharedPreferences sharedPreferences = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if ((sharedPreferences != null) && (sharedPreferences.contains("name"))){
            String name = sharedPreferences.getString("name", "");
            nameInput = findViewById(R.id.nameInput);
            nameInput.setText(name);
        }
    }

    protected void saveState() {
        SharedPreferences sharedPreferences = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        nameInput = findViewById(R.id.nameInput);
        editor.putString("name", nameInput.getText().toString());
        editor.commit();
    }

    protected void clearState() {
        SharedPreferences sharedPreferences = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
