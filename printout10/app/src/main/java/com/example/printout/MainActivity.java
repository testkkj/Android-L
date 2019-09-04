package com.example.printout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean bLog = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (bLog) {
            menu.getItem(0).setEnabled(true);
            menu.getItem(1).setEnabled(false);
        }else {
            menu.getItem(0).setEnabled(false);
            menu.getItem(1).setEnabled(true);
        }
        bLog = !bLog;
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.login:
                Toast.makeText(getApplicationContext(), "로그인", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                Toast.makeText(getApplicationContext(), "로그아웃", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.next:
                Toast.makeText(getApplicationContext(), "다음", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.setting:
                Toast.makeText(getApplicationContext(), "설정", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
