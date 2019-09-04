package com.example.challenge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 100;

    EditText editText;
    EditText editText1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MENU) {
            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText2);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editText.getText().toString();
                String pw = editText1.getText().toString();
                if (id.equals("")||pw.equals("")){
                    Toast.makeText(getApplicationContext(), "다 입력하시오!", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("pw", pw);
                    startActivityForResult(intent, REQUEST_CODE_MENU);
                }
            }
        });
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
        String id = editText.getText().toString();
        String pw = editText1.getText().toString();
        editor.putString("id", id);
        editor.putString("pw", pw);
        editor.commit();

    }

    protected void restore() {
        SharedPreferences sharedPreferences = getSharedPreferences("name", Activity.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");
        String pw = sharedPreferences.getString("pw", "");
        editText.setText(id);
        editText1.setText(pw);
    }
}
