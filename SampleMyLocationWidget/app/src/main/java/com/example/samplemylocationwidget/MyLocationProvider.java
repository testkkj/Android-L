package com.example.samplemylocationwidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;

import java.util.List;

public class MyLocationProvider extends AppWidgetProvider {

    public static double xcoord = 0.00;
    public static double ycoord = 0.00;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d("MyLocationProvider", "onUpdate() called : " + ycoord + "," + xcoord);

        final int size = appWidgetIds.length;

        for (int i = 0; i < size; i++) {
            int appWidgetId = appWidgetIds[i];

//            String uri = "geo:" + ycoord + "," + xcoord + "?z=10";
//            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));

            String uriBegin = "geo:" + ycoord + "," + xcoord;
            String query = ycoord + "," + xcoord + "(" + "내위치" + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=15";
            Uri uri = Uri.parse(uriString);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.mylocation);

            remoteViews.setOnClickPendingIntent(R.id.textView, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
        context.startService(new Intent(context, GPSLocationService.class));
    }

    public static class GPSLocationService extends Service {
        public static final String TAG = "GPSLocationService";

        private LocationManager locationManager = null;

        private LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG, "onLocationChanged() called : ");
                updateCoordinates(location.getLatitude(), location.getLongitude());
                stopSelf();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();

            Log.d(TAG, "onCreate() called : ");

            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startListening();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "onDestroy() called : ");
            stopListening();
        }

        private void startListening() {
            Log.d(TAG, "startListening() called : ");

            final Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(Criteria.POWER_LOW);

            final String bestProvider = locationManager.getBestProvider(criteria, true);
            try {
                if (bestProvider != null && bestProvider.length() > 0) {
                    locationManager.requestLocationUpdates(bestProvider, 500, 10, locationListener);
                } else {
                    final List<String> providers = locationManager.getProviders(true);

                    for (final String provider : providers) {
                        locationManager.requestLocationUpdates(provider, 500, 10, locationListener);
                    }
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

        private void stopListening() {
            try {
                if (locationManager != null && locationListener != null) {
                    locationManager.removeUpdates(locationListener);
                }
                locationManager = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void updateCoordinates(double latitude, double longitude) {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = null;
            String info = "";
            Log.d(TAG, "updateCoordinates() called.");
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 2);
                if (addresses != null && addresses.size() > 0) {
                    int addressCount = addresses.get(0).getMaxAddressLineIndex();
                    if (addressCount != -1) {
                        for (int index = 0; index <= addressCount; ++index) {
                            info += addresses.get(0).getAddressLine(index);
                            if (index < addressCount) {
                                info += ",";
                            }
                        }
                    } else {
                        info += addresses.get(0).getFeatureName() + "," + addresses.get(0).getSubAdminArea() + "," + addresses.get(0).getAdminArea();
                    }
                }
                Log.d(TAG, "Address : " + addresses.get(0).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (info.length() <= 0) {
                info = "[내 위치]" + latitude + "," + longitude + "\n터치하면 지도로 볼 수 있습니다.";
            } else {
                info += ("\n" + "[내 위치]" + latitude + "," + longitude + ")");
                info += "\n터치하면 지도로 볼 수 있습니다.";
            }

            RemoteViews views = new RemoteViews(getPackageName(), R.layout.mylocation);
            views.setTextViewText(R.id.textView, info);

            ComponentName componentName = new ComponentName(this, MyLocationProvider.class);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            appWidgetManager.updateAppWidget(componentName, views);

            xcoord = longitude;
            ycoord = latitude;
            Log.d(TAG, "coordinates : " + latitude + "," + longitude);
        }
    }
}


