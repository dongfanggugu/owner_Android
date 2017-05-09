package com.honyum.owner.utils;

import android.content.Context;

/**
 * Created by 李有鬼 on 2017/3/3 0003
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler crashHandler = null;

    private Context context;

    public static synchronized CrashHandler getInstance() {
        if (crashHandler == null) {
            crashHandler = new CrashHandler();
            return crashHandler;
        }
        return crashHandler;
    }

    public void init(Context context) {
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
    }
}
