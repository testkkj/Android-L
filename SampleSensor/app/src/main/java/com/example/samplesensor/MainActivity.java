package com.example.samplesensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    SensorManager sensorManager;
    List<Sensor> sensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSensorList();
            }
        });

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerFirstSensor();
            }
        });
    }

    public void getSensorList() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        int index = 0;
        for (Sensor sensor : sensors) {
            println("#" + index + " : " + sensor.getName());
        }
    }

    public void println(String data) {
        textView.append(data + "\n");
    }

    public void registerFirstSensor() {
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                String output = "Sensor Timestamp : " + sensorEvent.timestamp + "\n\n";
                for (int index = 0; index < sensorEvent.values.length; ++index) {
                    output += ("Sensor Value #" + (index + 1) + " : " + sensorEvent.values[index] + "\n");
                }
                println(output);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, sensors.get(0), SensorManager.SENSOR_DELAY_UI);
    }
}
