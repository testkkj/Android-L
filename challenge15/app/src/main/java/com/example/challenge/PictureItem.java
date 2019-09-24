package com.example.challenge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PictureItem extends LinearLayout {
    TextView dateText;
    ImageView imageView;
    BitmapFactory.Options options = new BitmapFactory.Options();

    public PictureItem(Context context) {
        super(context);
        init(context);
    }

    public PictureItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.picture, this, true);

        dateText = findViewById(R.id.dateText);
        imageView = findViewById(R.id.imageView);
        options.inSampleSize=4;
    }

    public void setDateText(String date) {
        dateText.setText(date);
    }

    public void setImageView(String  path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path,options);
        imageView.setImageBitmap(bitmap);
    }
}
