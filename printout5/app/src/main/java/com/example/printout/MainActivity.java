package com.example.printout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView.setText("onProgressChanged"+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView.setText("onStartTracingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("onStopTrackingTouch");
            }
        });
    }
}
