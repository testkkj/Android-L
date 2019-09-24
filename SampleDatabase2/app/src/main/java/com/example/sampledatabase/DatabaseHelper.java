package com.example.sampledatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String NAME = "testbase.db";
    public static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        println("onCreate 호출됨");

        String sql = "create table if not exists emp(_id integer primary key autoincrement, name text, age integer, mobile text)";

        sqLiteDatabase.execSQL(sql);
    }

    public void onOpen(SQLiteDatabase sqLiteDatabase) {
        println("onOpen 호출됨");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        println("onUpgrade 호출됨: " + i + "->" + i1);

        if (i1 > 1) {
            sqLiteDatabase.execSQL("drop table if exists emp");
        }
    }

    public void println(String data) {
        Log.d("DatabaseHelper", data);
    }
}
