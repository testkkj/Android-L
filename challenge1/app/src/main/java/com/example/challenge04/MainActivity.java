package com.example.challenge04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView2);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                byte[] bytes = null;
                try {
                    bytes = charSequence.toString().getBytes("KSC5601");
                    int a = bytes.length;
                    textView.setText(a + "/80바이트");
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String string = editable.toString();
                try{
                    byte[] bytes = string.getBytes("KSC5601");
                    if (bytes.length>80) {
                        editable.delete(editable.length()-2, editable.length()-1);
                    }
                }catch(UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
        };
        editText.addTextChangedListener(textWatcher);
    }

    public void onButtonClick(View view) {
        message();
    }

    private void message(){
        String msg = editText.getText().toString();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void onButton1Click(View view) {
        quit();
    }

    public void quit() {
        finish();
    }

}
