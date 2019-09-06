package com.example.challenge;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String inputFromService = intent.getStringExtra("input");
        Log.d("zb", inputFromService);
        Intent intentFromService = new Intent(getApplicationContext(), MyReceiver.class);
        intentFromService.setAction("a");
        intentFromService.putExtra("input", inputFromService);
        sendBroadcast(intentFromService);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
