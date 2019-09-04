package com.example.samplefragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ListFragment listFragment;
    ViewerFragment viewerFragment;

    int[] images = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.viewFragment);
    }

    public void onImageSelected(int position) {
        viewerFragment.imageView.setImageResource(images[position]);
    }

    /*
    public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelectionCallback {
    책 버전 위에는 프린트물
    ListFragment listFragment;
    ViewerFragment viewerFragment;

    int[] images = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        listFragment = (ListFragment) fragmentManager.findFragmentById(R.id.listFragment);
        viewerFragment = (ViewerFragment) fragmentManager.findFragmentById(R.id.viewFragment);
    }

    public void onImageSelected(int position) {
        viewerFragment.setImage(images[position]);
    }*/
}
