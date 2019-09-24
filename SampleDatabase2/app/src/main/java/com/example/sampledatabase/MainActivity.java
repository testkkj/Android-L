package com.example.sampledatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText, editText1;
    TextView textView;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper databaseHelper;
    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String databaseName = editText.getText().toString();
                createDatabase(databaseName);
            }
        });

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableName = editText1.getText().toString();
                createTable(tableName);

                insertRecord();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeQuery();
            }
        });
    }

    private void createDatabase(String name) {
        println("createDatabase 호출됨.");

        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        println("데이터베이스 생성함: " + name);
    }

    private void createTable(String name) {
        println("createTable 호출됨.");

        if (databaseList() == null) {
            println("데이터베이스를 먼저 생성하세요.");
            return;
        }

        sqLiteDatabase.execSQL("create table if not exists " + name + "( _id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)");
    }

    private void insertRecord() {

        println("insertRecord 호출됨.");
        if (sqLiteDatabase == null) {
            println("데이터베이스를 먼저 생성하세요.");
            return;
        }

        if (tableName == null) {
            println("테이블을 먼저 생성하세요.");
            return;
        }

        sqLiteDatabase.execSQL("insert into " + tableName + "(name, age, mobile) values ('john', 20, '010-1000 - 1000')");
        println("레코드 추가함.");
    }

    public void println(String data) {
        textView.append(data + "\n");
    }

    public void executeQuery() {
        println("executeQuery 호출됨.");

        Cursor cursor = sqLiteDatabase.rawQuery("select _id, name, age, mobile from testtable", null);
        int recordCount = cursor.getCount();
        println("레코드 개수: " + recordCount);

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String mobile = cursor.getString(3);

            println("레코드#" + i + " : " + id + ", " + name + ", " + age + ", " + mobile);
        }
        cursor.close();
    }
}
