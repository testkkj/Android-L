package com.example.printout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    Button button1;
    EditText editText;
    TextView textView1;
    Button button2;
    ImageView imageView;
    ImageView imageView1;
    int bc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button2);
        editText = findViewById(R.id.editText);
        textView1 = findViewById(R.id.textView2);
        button2 = findViewById(R.id.button3);
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String con  = editText.getText().toString();
                textView1.setText(con);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                byte[] bytea = null;
                try{
                    bytea = charSequence.toString().getBytes("KSC5601");
                    int len = bytea.length;
                    textView.setText(len+"/30 바이트");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bc==0){
                    imageView.setVisibility(View.INVISIBLE);
                    imageView1.setVisibility(View.VISIBLE);
                    button2.setText("◁");
                    bc=1;
                }else if (bc==1){
                    imageView.setVisibility(View.VISIBLE);
                    imageView1.setVisibility(View.INVISIBLE);
                    button2.setText("▷");
                    bc=0;
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            showToast("방향 : ORIENTATION_LANDSCAPE");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            showToast("방향 : ORIENTATION_PORTRAIT");
        }
    }

    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}