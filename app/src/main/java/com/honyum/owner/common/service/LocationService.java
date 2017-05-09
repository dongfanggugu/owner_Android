package com.honyum.owner.common.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.honyum.owner.base.Constant;

public class LocationService extends Service {

    private LocationClient mLocationClient;

    private BDLocationListener mBDLocationListener;

    public LocationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mLocationClient = new LocationClient(getApplicationContext());
        mBDLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mBDLocationListener);


        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    public void requestLocation() {
        if (mLocationClient != null) {
            mLocationClient.requestLocation();
        }
    }

    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            int type = bdLocation.getLocType();

            Log.d("AAA", "LocType==>>" + type);

            if (type == 61 || type == 66 || type == 161) {
                //定位成功了
                Log.d("AAA", "定位成功");

                Intent intent = new Intent();
                intent.setAction(Constant.ACTION_LOCATION_COMPLETE);
                intent.putExtra("success", true);
                intent.putExtra("lat", bdLocation.getLatitude());
                intent.putExtra("lng", bdLocation.getLongitude());
                sendBroadcast(intent);
            } else {
                //定位失败了
                Log.d("AAA", "定位失败");

                Intent intent = new Intent();
                intent.setAction(Constant.ACTION_LOCATION_COMPLETE);
                intent.putExtra("success", false);
                sendBroadcast(intent);
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mLocationClient != null) {
            mLocationClient.start();
            Log.d("AAA", "启动定位");
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null && mBDLocationListener != null) {
            mLocationClient.stop();
            mLocationClient.unRegisterLocationListener(mBDLocationListener);
        }
        Log.d("AAA", "Service销毁");
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (mLocationClient != null) {
            mLocationClient.start();
        }
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public LocationService getService() {
            return LocationService.this;
        }
    }
}
