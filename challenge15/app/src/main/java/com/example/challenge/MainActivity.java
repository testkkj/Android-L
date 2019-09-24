package com.example.challenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    FrameLayout container1;
    FrameLayout container2;
    Animation translate_in;
    PictureItem picture1;
    PictureItem picture2;
    TextView countText;
    Handler handler = new Handler();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM--dd HH:mm");

    ArrayList<ImageInfo> pictures;
    boolean running = false;
    int curPage = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countText = findViewById(R.id.countText);

        container1 = findViewById(R.id.container);
        picture1 = new PictureItem(this);
        container1.addView(picture1);

        container2 = findViewById(R.id.container1);
        picture2 = new PictureItem(this);
        container2.addView(picture2);

        translate_in = AnimationUtils.loadAnimation(this, R.anim.translate_in);

        Button startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictures = queryAllPictures();
                AnimationThread thread = new AnimationThread();
                thread.start();
            }
        });

        Button stopBtn = findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    private ArrayList<ImageInfo> queryAllPictures() {
        ArrayList<ImageInfo> result = new ArrayList<ImageInfo>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME, MediaStore.MediaColumns.DATE_ADDED};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");

        while (cursor.moveToNext()) {
            int dataIndex = cursor.getColumnIndexOrThrow((MediaStore.MediaColumns.DATA));
            int nameIndex = cursor.getColumnIndexOrThrow((MediaStore.MediaColumns.DISPLAY_NAME));
            int dateIndex = cursor.getColumnIndexOrThrow((MediaStore.MediaColumns.DATE_ADDED));
            String path = cursor.getString(dataIndex);
            String displayName = cursor.getString(nameIndex);
            String outDate = cursor.getString(dateIndex);
            String dateInput = dateFormat.format(new Date(new Long(outDate).longValue() * 1000L));

            if (!TextUtils.isEmpty(path)) {
                ImageInfo info = new ImageInfo(path, displayName, dateInput);
                result.add(info);
            }
            count++;
        }
        return result;
    }

    private class AnimationThread extends Thread {
        @Override
        public void run() {
            running = true;
            while (running) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ImageInfo info = pictures.get(curPage);
                        picture1.setDateText(info.getDateInput());
                        picture1.setImageView(info.getPath());
                        picture1.startAnimation(translate_in);

                        ImageInfo info1 = pictures.get(curPage + 1);
                        picture2.setDateText(info1.getDateInput());
                        picture2.setImageView(info1.getPath());
                        picture2.startAnimation(translate_in);

                        curPage += 1;
                        if (curPage > count) {
                            curPage = 0;
                        }
                        countText.setText(curPage + " / " + count);
                    }
                });
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }
}