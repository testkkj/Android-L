package com.example.challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFromActivity = new Intent(getApplicationContext(), MyService.class);
                intentFromActivity.putExtra("input", editText.getText().toString());
                Log.d("za", intentFromActivity.getStringExtra("input"));
                startService(intentFromActivity);
            }
        });

        Intent intentFromReceiver = getIntent();
        processIntent(intentFromReceiver);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null)  {
            String input = intent.getStringExtra("input");
            textView.setText(input);
        }
    }
}
