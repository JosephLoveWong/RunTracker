package com.promiseland.runtracker.bean;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by joseph on 2016/8/13.
 */
public class RunManager {
    private static final String TAG = "RunManager";
    public static final String ACTION_LOCATION = "com.promiseland.runtracker.ACTION_LOCATION";

    private static RunManager sInstance;
    private Context mContext;
    private LocationManager mLocationManager;

    private RunManager(Context context) {
        mContext = context.getApplicationContext();
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public static RunManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (RunManager.class) {
                if (sInstance == null) {
                    sInstance = new RunManager(context);
                }
            }
        }
        return sInstance;
    }

    public PendingIntent getLocationPendingIntent(boolean shouldCreate) {
        Intent broadcast = new Intent(ACTION_LOCATION);
        int flags = shouldCreate ? 0 : PendingIntent.FLAG_NO_CREATE;
        return PendingIntent.getBroadcast(mContext, 0, broadcast, flags);
    }

    public void startLocationUpdates() {
        String provider = LocationManager.GPS_PROVIDER;
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location lastKnown = mLocationManager.getLastKnownLocation(provider);
        if (lastKnown != null) {
            lastKnown.setTime(System.currentTimeMillis());
            broadcastLocation(lastKnown);
        }

        PendingIntent locationPendingIntent = getLocationPendingIntent(true);
        mLocationManager.requestLocationUpdates(provider, 0, 0, locationPendingIntent);
    }

    public void stopLocationUpdates(){
        PendingIntent locationPendingIntent = getLocationPendingIntent(false);
        if(locationPendingIntent != null){
            mLocationManager.removeUpdates(locationPendingIntent);
            locationPendingIntent.cancel();
        }
    }

    public boolean isTrackingRun(){
        return getLocationPendingIntent(false) != null;
    }

    private void broadcastLocation(Location location) {
        Intent broadcast = new Intent(ACTION_LOCATION);
        broadcast.putExtra(LocationManager.KEY_LOCATION_CHANGED, location);
        mContext.sendBroadcast(broadcast);
    }


}
