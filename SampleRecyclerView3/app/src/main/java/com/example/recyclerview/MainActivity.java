package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        personAdapter = new PersonAdapter();

        personAdapter.addItem(new Person("김민수", "000-0000-0000"));
        personAdapter.addItem(new Person("이민수", "100-1000-1000"));
        personAdapter.addItem(new Person("박민수", "200-2000-2000"));
        personAdapter.addItem(new Person("천민수", "300-3000-3000"));
        personAdapter.addItem(new Person("방민수", "400-4000-4000"));
        personAdapter.addItem(new Person("지민수", "500-5000-5000"));
        personAdapter.addItem(new Person("축민수", "600-6000-6000"));
        personAdapter.addItem(new Person("마민수", "700-7000-7000"));
        personAdapter.addItem(new Person("골민수", "800-8000-8000"));
        personAdapter.addItem(new Person("피민수", "900-9000-9000"));
        personAdapter.addItem(new Person("최민수", "010-0100-0100"));
        personAdapter.addItem(new Person("가민수", "011-1100-1100"));
        personAdapter.addItem(new Person("나민수", "012-1200-1200"));

        recyclerView.setAdapter(personAdapter);

        personAdapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder viewHolder, View view, int position) {
                Person item = personAdapter.getItem(position);
                Toast.makeText(getApplicationContext(), "아이템 선택됨 : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
