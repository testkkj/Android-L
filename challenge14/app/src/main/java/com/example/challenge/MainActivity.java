package com.example.challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    FrameLayout container;
    Animation translateIn;
    Animation translateOut;

    Customer view1;
    Customer view2;

    int selected = 1;

    boolean runngin = false;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);

        view1 = new Customer(this);
        view1.setName("kimjunsu");
        view1.setMobile("010-0000-0000");
        view1.setAddress("seoul");
        container.addView(view1);

        view2 = new Customer(this);
        view2.setName("leeheesun");
        view2.setMobile("010-1111-1111");
        view2.setAddress("suncheon");
        container.addView(view2);

        translateIn = AnimationUtils.loadAnimation(this, R.anim.translate_in);
        translateOut = AnimationUtils.loadAnimation(this, R.anim.translate_out);

        Button startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationThread thread = new AnimationThread();
                thread.start();
            }
        });

        Button stopBtn = findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runngin=false;
            }
        });
    }

    class AnimationThread extends Thread {
        @Override
        public void run() {
            runngin = true;
            while (runngin){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (selected ==0 ){
                            view1.setAnimation(translateIn);
                            view2.startAnimation(translateOut);
                        }else {
                            view1.setAnimation(translateOut);
                            view2.startAnimation(translateIn);
                        }
                    }
                });
                selected += 1;
                if (selected>1){
                    selected=0;
                }
                try {
                    Thread.sleep(1000);;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
