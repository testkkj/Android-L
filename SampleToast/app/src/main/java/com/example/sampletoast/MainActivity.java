package com.example.sampletoast;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText2);
    }

    public void onButton1Clicked(View view){
        try{
            Toast toast = Toast.makeText(this, "위치가 바뀐 토스트 메시지입니다.", Toast.LENGTH_LONG);
            int xOffset = Integer.parseInt(editText.getText().toString());
            int yOffset = Integer.parseInt(editText1.getText().toString());

            toast.setGravity(Gravity.TOP|Gravity.LEFT, xOffset, yOffset);
            toast.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onButton2Clicked(View view) {
        LayoutInflater layoutInflater = getLayoutInflater();

        View layout = layoutInflater.inflate(R.layout.toastborder, (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView textView = layout.findViewById(R.id.text);

        Toast toast = new Toast(this);
        textView.setText("모양 바꾼 토스트");
        toast.setGravity(Gravity.CENTER,0,-100);
        toast.setDuration(toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void onButton3Clicked(View view){
        Snackbar.make(view, "스낵바입니다.", Snackbar.LENGTH_LONG).show();
    }
}
