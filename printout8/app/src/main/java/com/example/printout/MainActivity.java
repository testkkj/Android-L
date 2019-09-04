package com.example.printout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        restore();
    }

    @Override
    protected void onStop() {
        super.onStop();
        save();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save();
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restore();
    }

    protected void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("name", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("a", editText.getText().toString());
        editor.commit();
    }

    protected void restore() {
        SharedPreferences sharedPreferences = getSharedPreferences("name", Activity.MODE_PRIVATE);
        if ((sharedPreferences != null) && (sharedPreferences.contains("a"))) {
            String name = sharedPreferences.getString("a", "");
            editText.setText(name);
        }
    }
}
