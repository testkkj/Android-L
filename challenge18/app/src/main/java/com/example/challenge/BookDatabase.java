package com.example.challenge;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

public class BookDatabase {
    public static final String TAG = "BookDatabase";
    private static BookDatabase database;
    public static String DATABASE_NAME = "book.db";
    public static String TABLE_BOOK_INFO = "BOOK_INFO";
    public static int DATABASE_VERSION = 1;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    private BookDatabase(Context context) {
        this.context = context;
    }

    public static BookDatabase getInstance(Context context) {
        if (database == null) {
            database = new BookDatabase(context);
        }
        return database;
    }

    public boolean open() {
        println("opening database [" + DATABASE_NAME + "].");
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return true;
    }

    public void close() {
        println("closing database [" + DATABASE_NAME + "].");
        db.close();
        database = null;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            println("creating table [" + TABLE_BOOK_INFO + "].");
            String CREATE_SQL = "create table " + TABLE_BOOK_INFO + "("
                    + " _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + " NAME TEXT, "
                    + " AUTHOR TEXT, "
                    + " CONTENTS TEXT) ";
            sqLiteDatabase.execSQL(CREATE_SQL);
            InsertRecord(sqLiteDatabase, "Do it! 안드로이드 1 프로그래밍", "정재곤", "안드로이드 기본서로 이지스 퍼블리싱 출판사에서 출판했습니다.");
            InsertRecord(sqLiteDatabase, "Do it! 안드로이드 2 프로그래밍", "정재곤", "안드로이드 기본서로 이지스 퍼블리싱 출판사에서 출판했습니다.");
            InsertRecord(sqLiteDatabase, "Do it! 안드로이드 3 프로그래밍", "정재곤", "안드로이드 기본서로 이지스 퍼블리싱 출판사에서 출판했습니다.");
            InsertRecord(sqLiteDatabase, "Do it! 안드로이드 4 프로그래밍", "정재곤", "안드로이드 기본서로 이지스 퍼블리싱 출판사에서 출판했습니다.");
            InsertRecord(sqLiteDatabase, "Do it! 안드로이드 5 프로그래밍", "정재곤", "안드로이드 기본서로 이지스 퍼블리싱 출판사에서 출판했습니다.");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            println("Upgrading database from version " + i + " to " + i1 + ".");
            sqLiteDatabase.execSQL("drop table if exists " + TABLE_BOOK_INFO);
            onCreate(sqLiteDatabase);
        }

        private void InsertRecord(SQLiteDatabase _db, String name, String author, String contents) {
            _db.execSQL("insert into " + TABLE_BOOK_INFO + "(NAME, AUTHOR, CONTENTs) values ('+" +
                    name + "','" + author + "','" + contents + "');");
        }
    }

    public void InsertRecord(String name, String author, String contents) {
        db.execSQL("insert into " + TABLE_BOOK_INFO + "(NAME, AUTHOR, CONTENTs) values ('+" +
                name + "','" + author + "','" + contents + "');");
    }

    public ArrayList<BookInfo> selectAll() {
        ArrayList<BookInfo> result = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery("select NAME, AUTHOR, CONTENTS from" + TABLE_BOOK_INFO, null);
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String name = cursor.getString(0);
                String author = cursor.getString(1);
                String contents = cursor.getString(2);
                BookInfo info = new BookInfo(name, author, contents);
                result.add(info);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception in executing insert SQL.", e);
        }
        return result;
    }

    private void println(String msg) {
        Log.d(TAG, msg);
    }
}
