package com.example.challenge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Client extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Button button = findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivityForResult(intent,300);
            }
        });

        Button button1 = findViewById(R.id.button11);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent,301);
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ConsumerData consumerData = bundle.getParcelable("consumerData");
        String info = "이름 : " + consumerData.name + "\n나이 : " + consumerData.age + "\n성별 : " + consumerData.gender;
        textView = findViewById(R.id.textView7);
        textView.setText(info);
    }
}
