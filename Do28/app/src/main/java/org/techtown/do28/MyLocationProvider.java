package org.techtown.do28;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;
public class MyLocationProvider extends AppWidgetProvider {
    static String receiver = "+1 650 555-6789";
    static PendingIntent sentIntent;      //전송결과 응답 인텐트
    static PendingIntent deliveredIntent;  //SMS 수신결과 응답 인텐트
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
     super.onUpdate(context, appWidgetManager, appWidgetIds);
     if (sentIntent==null){
       sentIntent=PendingIntent.getBroadcast(context,0,
                                                        new Intent("SMS_SENT_ACTION"),0);
     }
     if (deliveredIntent==null){
         deliveredIntent=PendingIntent.getBroadcast(context,0,
                                                        new Intent("SMS_DELIVERED_ACTION"),0);
     }

     final int size = appWidgetIds.length;
     for (int i=0;i<size;i++){
         int appWidgetId = appWidgetIds[i];
         Intent sendIntent = new Intent(context,GPSLocationService.class);
         sendIntent.putExtra("command","send");
         sendIntent.putExtra("receiver",receiver);
         PendingIntent pendingIntent = PendingIntent.getService(context,0,sendIntent,0);
         RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.mylocation);
         views.setOnClickPendingIntent(R.id.button,pendingIntent);
         appWidgetManager.updateAppWidget(appWidgetId,views);
     }

     Intent startIntent = new Intent(context,GPSLocationService.class);
     startIntent.putExtra("command","start");
     context.startService(startIntent);
    }   //onUpdate() 끝

    public static class GPSLocationService extends Service {
        static double ycoord = 0.0d;
        static double xcoord = 0.0d;
        LocationManager manager = null;
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateCoordinates(location.getLatitude(),location.getLongitude());
                stopSelf();
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {}
            @Override
            public void onProviderEnabled(String s) {}
            @Override
            public void onProviderDisabled(String s) {}
        };

        private void updateCoordinates(double latitude, double longitude) {
            Geocoder coder = new Geocoder(this);
            List<Address> addresses = null;
            String info = "";
            try {
                addresses = coder.getFromLocation(latitude, longitude, 2);
                if (addresses != null && addresses.size() > 0) {
                    int addressCount = addresses.get(0).getMaxAddressLineIndex();
                    if (addressCount !=-1){
                        for (int i=0; i<=addressCount;i++){
                            info=info+addresses.get(0).getAddressLine(i);
                            if (i<addressCount){
                                info= info+", ";
                            }
                        }
                    }else {
                        info=info+addresses.get(0).getFeatureName()+", "
                                + addresses.get(0).getSubAdminArea()+", "
                                + addresses.get(0).getAdminArea();
                    }
                }
            }catch (Exception e){}

            if (info.length()<=0){
                info = "[내 위치] " + latitude + ", " + longitude;
            }else {
                info = "\n[내 위치] " + latitude + ", " + longitude;
            }
            xcoord=longitude;
            ycoord=latitude;
        }    //updateCoordinates 끝

        BroadcastReceiver sentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK: //전송 성공
                        Toast.makeText(context,"내 위치\n" + ycoord + ", " + xcoord,
                                        Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE: //전송 실패
                        Toast.makeText(context,"전송실패",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        BroadcastReceiver deliveredReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK: //도착 완료
                        Toast.makeText(context,"SMS도착완료",Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED: //도착 안됨
                        Toast.makeText(context,"SMS도착실패",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        @Override
        public void onCreate() {
            super.onCreate();
            manager = (LocationManager) getSystemService(LOCATION_SERVICE);
            registerReceiver(sentReceiver,new IntentFilter("SMS_SENT_ACTION"));
            registerReceiver(deliveredReceiver,new IntentFilter("SMS_DELIVERED_ACTION"));
                 //manifests에 추가하지않고 소스파일에서 브로드캐스트수신자 등록
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            if (intent !=null){
                String command = intent.getStringExtra("command");
                if (command!=null){
                    if (command.equals("start")){
                        startListening();
                    }else if (command.equals("send")){
                    String receiver = intent.getStringExtra("receiver");
                    String contents = "내 위치 : " + ycoord + ", " + xcoord;

                    SmsManager smsManager = SmsManager.getDefault();
                    try {
                        smsManager.sendTextMessage(receiver, null, contents, sentIntent, deliveredIntent);
                    }catch (SecurityException e){}    //예외처리 안하면 문자 전송시 0.0, 0.0 표시
                    }
                }
            }
            return super.onStartCommand(intent,flags,startId);
        }  //onStartCommand() 끝

        private void startListening() {
            final Criteria criteria = new Criteria();  //최적의 프로바이더를선택하기위한 조건을 지정한다
            criteria.setAccuracy(Criteria.ACCURACY_COARSE); //정확도
            criteria.setAltitudeRequired(false); //고도정보 x
            criteria.setBearingRequired(false); //방위각정보 x
            criteria.setCostAllowed(true);  //추가비용 0
            criteria.setPowerRequirement(Criteria.POWER_LOW);//전원사용량

            final String bestProvider = manager.getBestProvider(criteria,true);
            try {
                if (bestProvider != null && bestProvider.length() > 0) {
                    manager.requestLocationUpdates(bestProvider, 500, 10, listener);
                } else {
                    List<String> providers = manager.getProviders(true);
                    for (String provider : providers) {
                        manager.requestLocationUpdates(provider, 500, 10, listener);
                    }
                }
            }catch (SecurityException e){e.printStackTrace();}
        }  //startListening() 끝

        @Override
        public void onDestroy() {
            if (manager !=null && listener !=null ){
                manager.removeUpdates(listener); }
            manager=null;
            unregisterReceiver(sentReceiver);
            unregisterReceiver(deliveredReceiver);
            super.onDestroy();
        }
        @Nullable  @Override
        public IBinder onBind(Intent intent) {  return null;  }
    }  //GPSLocationService 클래스 끝
}
