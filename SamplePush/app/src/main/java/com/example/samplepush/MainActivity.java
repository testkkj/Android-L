package com.example.samplepush;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.d("inid", newToken);

                println("등록id : " + newToken);
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String instanceId = FirebaseInstanceId.getInstance().getId();

                println("확인된 인스턴스 id : " + instanceId);
            }
        });
    }

    public void println(String data) {
        textView1.append(data + "\n");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        println("onNewIntent() 호출됨");

        if (intent != null) {
            processIntent(intent);
        }
    }

    private void processIntent(Intent intent) {
        String from = intent.getStringExtra("from");
        if (from == null) {
            println("from is null");
            return;
        }

        String contents = intent.getStringExtra("contents");

        println("DATA : " + from + " , " + contents);
        textView.setText("[" + from + "]으로부터 수신한 데이터 : " + contents);
    }
}
