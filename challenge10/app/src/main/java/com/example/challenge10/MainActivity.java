package com.example.challenge10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ClientAdapter clientAdapter;

    EditText editText;
    EditText editText1;
    EditText editText2;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        clientAdapter = new ClientAdapter();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

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

        clientAdapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(ClientAdapter.ViewHolder viewHolder, View view, int position) {
                Client client = clientAdapter.getItem(position);
                Toast.makeText(getApplicationContext(), "제품 이름 : " + client.getName() + "제품 가격 : " + client.getBirth(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
