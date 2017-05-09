package com.honyum.owner.common.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.honyum.owner.R;
import com.honyum.owner.activity.HomePageActivity;
import com.honyum.owner.base.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;


public class MyJPushReceiver extends BroadcastReceiver {

    private static final String TAG = "JPush";

    public MyJPushReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        Bundle bundle = intent.getExtras();

        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            processMessage(context, bundle);
        }
    }


    /**
     * 处理接受到的推送消息
     */
    private void processMessage(Context context, Bundle bundle) {

        String extraMsg = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String msg = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String msgId = bundle.getString(JPushInterface.EXTRA_MSG_ID);

        Log.d(TAG, "extraMsg: " + extraMsg);
        Log.d(TAG, "msg: " + msg);
        Log.d(TAG, "msgId: " + msgId);

        try {

            JSONObject jsonObject = new JSONObject(extraMsg);
            String notifyType = jsonObject.getString("notifyType");

            Log.d(TAG, "notifyType: " + notifyType);

            if (BaseActivity.isBackGround()) {
                sendNotify(context, HomePageActivity.class, msg);
            } else {
                sendToPage(context, msg);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public interface onReceiveMsgListener {
        void onReceiveMsg(String msg);
    }

    private static onReceiveMsgListener onReceiveMsgListener;

    public static void setOnReceiveMsgListener(MyJPushReceiver.onReceiveMsgListener onReceiveMsgListener) {
        MyJPushReceiver.onReceiveMsgListener = onReceiveMsgListener;
    }

    private void sendToPage(Context context, String msg) {
        if (onReceiveMsgListener != null) {
            onReceiveMsgListener.onReceiveMsg(msg);
        }
    }

    private void sendNotify(Context context, Class<?> cls, String msg) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.mipmap.logo);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.app_logo));
        builder.setContentTitle("新消息");
        builder.setContentText(msg);

        Intent intent = new Intent(context, cls);
        PendingIntent pendingIntent
                = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setFullScreenIntent(pendingIntent, true);

        final NotificationManager manager
                = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();

        manager.notify(0, notification);
    }
}
