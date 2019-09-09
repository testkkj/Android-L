package com.example.challenge10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText1;
    EditText editText2;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final ClientAdapter clientAdapter = new ClientAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = findViewById(R.id.editText);
                editText1 = findViewById(R.id.editText2);
                editText2 = findViewById(R.id.editText3);

                String name = editText.getText().toString();
                String birth = editText1.getText().toString();
                String mobile = editText2.getText().toString();

                clientAdapter.addItem(new Client(name, birth, mobile));

                recyclerView.setAdapter(clientAdapter);
            }

        });

    }
}
