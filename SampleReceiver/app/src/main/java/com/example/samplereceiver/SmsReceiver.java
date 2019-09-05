package com.example.samplereceiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG = "SmsReceiver";

    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(TAG, "onReceice() called");

        Bundle bundle = intent.getExtras();
        SmsMessage[] smsMessages = parseSmsMessage(bundle);

        if (smsMessages != null && smsMessages.length > 0) {
            String sender = smsMessages[0].getOriginatingAddress();
            Log.i(TAG, "SMS sender : " + sender);

            String contents = smsMessages[0].getMessageBody();
            Log.i(TAG, "SMS contents : " + contents);

            Date receivedDate = new Date(smsMessages[0].getTimestampMillis());
            Log.i(TAG, "SMS receivedDate : " + receivedDate.toString());

            sendToActivity(context, sender, contents, receivedDate);
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {

        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] smsMessages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for (int i = 0; i < smsCount; i++) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }

        return smsMessages;
    }

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate) {
        Intent myIntent = new Intent(context, SmsActivity.class);

        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        myIntent.putExtra("sender", sender);
        myIntent.putExtra("contents", contents);
        myIntent.putExtra("receivedDate", simpleDateFormat.format(receivedDate));

        context.startActivity(myIntent);
    }
}
