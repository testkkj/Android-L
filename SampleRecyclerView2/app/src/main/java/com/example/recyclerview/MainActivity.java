package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        PersonAdapter personAdapter = new PersonAdapter();

        personAdapter.addItem(new Person("김민수", "000-0000-0000"));
        personAdapter.addItem(new Person("이민수", "100-1000-1000"));
        personAdapter.addItem(new Person("박민수", "200-2000-2000"));

        recyclerView.setAdapter(personAdapter);
    }
}
