package com.example.challenge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String input = intent.getStringExtra("input");

        sendToActivity(context, input);
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objects = (Object[]) bundle.get("puds");
        SmsMessage[] smsMessages = new SmsMessage[objects.length];

        int smsCount = objects.length;
        for (int i = 0; i < smsCount; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) objects[i], format);
            } else {
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
            }
        }
        return smsMessages;
    }

    private void sendToActivity(Context context, String input) {
        Intent intentFromService = new Intent(context, MainActivity.class);
        intentFromService.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentFromService.putExtra("input", input);
        context.startActivity(intentFromService);
    }
}
