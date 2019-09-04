package com.example.printout2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    ImageView imageView;
    BitmapDrawable bitmapDrawable;
    int imageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollView);
        imageView = findViewById(R.id.imageView);
        scrollView.setHorizontalScrollBarEnabled(true);

    }

    public void onButtonClicked(View view) {
        changeImage();
    }

    private void changeImage() {
        if (imageNumber == 0) {
            Resources resources = getResources();
            bitmapDrawable = (BitmapDrawable) resources.getDrawable(R.drawable.image01);
            int bitmapWidth = bitmapDrawable.getIntrinsicWidth();
            int bitmapHeight = bitmapDrawable.getIntrinsicHeight();

            imageView.setImageDrawable(bitmapDrawable);
            imageView.getLayoutParams().width = bitmapWidth;
            imageView.getLayoutParams().height = bitmapHeight;
            imageNumber = 1;
        }else if (imageNumber == 1){
            Resources resources = getResources();
            bitmapDrawable = (BitmapDrawable) resources.getDrawable(R.drawable.image02);
            int bitmapWidth = bitmapDrawable.getIntrinsicWidth();
            int bitmapHeight = bitmapDrawable.getIntrinsicHeight();

            imageView.setImageDrawable(bitmapDrawable);
            imageView.getLayoutParams().width = bitmapWidth;
            imageView.getLayoutParams().height = bitmapHeight;
            imageNumber = 0;
        }
    }
}