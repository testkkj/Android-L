package com.example.samplemanager;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button, button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getServiceList();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                getCurrentActivity();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApplist();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findActivity();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
            }
        });
    }

    public void getServiceList() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfos = activityManager.getRunningAppProcesses();

        for (int i = 0; i < runningAppProcessInfos.size(); i++) {
            ActivityManager.RunningAppProcessInfo info = runningAppProcessInfos.get(i);
            println("#" + i + "->" + info.pid + "," + info.processName);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void getCurrentActivity() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);

        for (int i = 0; i < runningTaskInfos.size(); i++) {
            ActivityManager.RunningTaskInfo info = runningTaskInfos.get(i);
            println("Running Task ->" + info.topActivity.toString());
        }
    }

    public void getApplist() {
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> applicationInfos = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (int i = 0; i < applicationInfos.size(); i++) {
            ApplicationInfo info = applicationInfos.get(i);
            println("#" + i + "->" + info.loadLabel(packageManager).toString() + "," + info.packageName);
        }
    }

    public void findActivity() {
        PackageManager packageManager = getPackageManager();

        Intent intent = new Intent(this, MainActivity.class);
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);

        for (int i = 0; i < resolveInfoList.size(); i++) {
            ResolveInfo info = resolveInfoList.get(i);
            println("#" + i + "->" + info.activityInfo.applicationInfo.packageName);
        }
    }

    public void setAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, pendingIntent);
    }

    public void println(String data) {
        textView.append(data + "\n|");
    }
}