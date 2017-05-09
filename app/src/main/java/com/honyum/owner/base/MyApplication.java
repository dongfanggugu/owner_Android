package com.honyum.owner.base;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import cn.jpush.android.api.JPushInterface;


public class MyApplication extends Application {

    public static final boolean IS_SHOW_LOG = true;

    private static Config config;

    private BaseActivity myBaseActivity;

    @Override
    public void onCreate() {
        super.onCreate();

        config = new Config(this);

        SDKInitializer.initialize(this);

        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
    }

    public static Config getConfig() {
        return config;
    }

    public BaseActivity getMyBaseActivity() {
        return myBaseActivity;
    }

    public void setMyBaseActivity(BaseActivity myBaseActivity) {
        this.myBaseActivity = myBaseActivity;
    }
}
