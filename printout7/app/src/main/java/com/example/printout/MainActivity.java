package com.example.printout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;
    public static final String KEY_BOOK_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                BookData bookData = new BookData(1, "멈추면, 비로소 보이는 것들", "혜민", "쌤앤파커스", 14000);
                intent.putExtra(KEY_BOOK_DATA, bookData);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }
}
