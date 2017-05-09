package com.honyum.owner.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.honyum.owner.R;
import com.honyum.owner.activity.HomePageActivity;
import com.honyum.owner.common.receiver.MyJPushReceiver;
import com.honyum.owner.net.VersionCheckRequest;
import com.honyum.owner.net.VersionCheckResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.NetWorkManager;
import com.honyum.owner.net.base.RequestBean;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.DownloadFilesTask;
import com.honyum.owner.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


public class BaseActivity extends AppCompatActivity {

    /**
     * 处理网路请求的错误信息
     */
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            if (null == msg.obj) {
                return;
            }
            if (-1 == msg.arg1) {
                String obj = (String) msg.obj;
                String[] split = obj.split(":");

                if (split.length == 1) {
                    showToast(split[0]);
                } else {
                    showToast(split[1]);
                }

                //登录超时,回到登录页面
                if (-9 == msg.arg2) {
                    clearUserInfo();

                    Intent intent = new Intent(BaseActivity.this, HomePageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                } else if (-10 == msg.arg2) {
                    //网络没有连接
                }
            }
        }
    };

    public Handler getHandler() {
        return mHandler;
    }

    private Toast toast;


    private static boolean backGround;

    public static boolean isBackGround() {
        return backGround;
    }


    @Override
    protected void onPause() {
        super.onPause();
        backGround = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        backGround = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMyApplication().setMyBaseActivity(this);

        receiveJPushMsg();
    }

    private void receiveJPushMsg() {
        MyJPushReceiver.setOnReceiveMsgListener(new MyJPushReceiver.onReceiveMsgListener() {
            @Override
            public void onReceiveMsg(String msg) {
                showMsgDialog(msg);
            }
        });
    }

    private void showMsgDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("消息提示");
        builder.setMessage(msg);
        builder.setPositiveButton("确认", null);
        builder.create().show();
    }

    /**
     * 注册JPush
     */
    protected void registerJPush(final String token) {
        //设置设备的推送别名
        String alias = token.replaceAll("-", "_");

        JPushInterface.setAlias(BaseActivity.this, alias, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {

            }
        });
    }

    /**
     * 获取全局application对象
     */
    protected MyApplication getMyApplication() {
        return (MyApplication) getApplication();
    }


    /**
     * 获取Config对象
     */
    public Config getConfig() {
        return getMyApplication().getConfig();
    }


    /**
     * 添加不带进度条的网络请求任务
     */
    public void addBackGroundTask(NetTask task) {
        addTask(task, false, null, null);
    }

    /**
     * 添加带有进度条的网络请求任务
     */
    public void addTask(NetTask task) {
        addTask(task, true, null, null);
    }

    public void addTask(NetTask task, String title) {
        addTask(task, true, title, null);
    }

    public void addTask(NetTask task, String title, String info) {
        addTask(task, true, title, info);
    }

    /**
     * 发送任务,选择是否显示进度条
     */
    public void addTask(NetTask task, boolean showProgress, String title,
                        String info) {
        NetWorkManager netManager = NetWorkManager.getInstance();
        task.setBaseActivity(this);
        if (showProgress) {
            task.setSyncRequest();
        }
        if (!netManager.addTast(task, title, info)) {
            Toast.makeText(this, "网络连接错误，请检查网络设置", Toast.LENGTH_LONG).show();
        }
    }


    public void initTitleBar(int titleId, String title,
                             int leftRes, View.OnClickListener LeftListener,
                             int rightRes, View.OnClickListener rightListener) {

        View titleView = findViewById(titleId);

        ((TextView) titleView.findViewById(R.id.tv_title)).setText(title);

        if (leftRes != 0) {
            ImageView leftButton = (ImageView) titleView
                    .findViewById(R.id.iv_left);
            leftButton.setImageResource(leftRes);
            leftButton.setVisibility(View.VISIBLE);
            leftButton.setOnClickListener(LeftListener);
        }

        if (rightRes != 0) {
            ImageView rightButton = (ImageView) titleView
                    .findViewById(R.id.iv_right);
            rightButton.setImageResource(rightRes);
            rightButton.setVisibility(View.VISIBLE);
            rightButton.setOnClickListener(rightListener);
        }
    }

    public void initTitleBar(int titleId, String title,
                             int leftRes, View.OnClickListener LeftListener,
                             String rightStr, View.OnClickListener rightListener) {

        View titleView = findViewById(titleId);

        ((TextView) titleView.findViewById(R.id.tv_title)).setText(title);

        if (leftRes != 0) {
            ImageView leftButton = (ImageView) titleView
                    .findViewById(R.id.iv_left);
            leftButton.setImageResource(leftRes);
            leftButton.setOnClickListener(LeftListener);
        }

        if (!Utils.isEmpty(rightStr)) {
            TextView rightButton = (TextView) titleView
                    .findViewById(R.id.tv_right);
            rightButton.setText(rightStr);
            rightButton.setOnClickListener(rightListener);
        }
    }

    public View.OnClickListener backClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };

    /**
     * 显示toast提示
     */
    public void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }

    /**
     * 显示log提示
     */
    public void showLog(String msg) {
        if (MyApplication.IS_SHOW_LOG) {
            Log.d("AAA", msg);
        }
    }


    /**
     * 非强制更新时点击取消时的callback
     */
    public interface IUpdateCancelCallback {
        public void onCancel();
    }


    /**
     * 更新apk
     * 描述
     */
    public void updateApk(final String url, int status, String description,
                          final IUpdateCancelCallback callback) {
        final ProgressDialog pBar = new ProgressDialog(this);
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setTitle("版本更新");
        pBar.setMessage("正在下载中，请稍后...");
        pBar.setCancelable(false);
        pBar.setMax(100);
        pBar.setProgress(0);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Dialog dialog = null;
        builder.setTitle("有新版本更新");
        builder.setCancelable(false);
        switch (status) {
            case 1:
                builder.setMessage(description)
                        .setPositiveButton("更新",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        pBar.show();
                                        new DownloadFilesTask(BaseActivity.this, pBar)
                                                .execute(url);
                                    }
                                }).setNegativeButton("退出",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                finish();
                            }
                        });
                dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                //dialog.setCancelable(false);
                dialog.show();
                break;
            case 0:// 非强制更新
                builder.setMessage(description)
                        .setPositiveButton("更新",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        pBar.show();
                                        new DownloadFilesTask(BaseActivity.this, pBar)
                                                .execute(url);
                                    }
                                }).setNegativeButton("下次再说",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                if (callback != null) {
                                    callback.onCancel();
                                }
                            }
                        });
                dialog = builder.create();
                dialog.show();
                break;
        }
    }


    /**
     * 检测服务器版本
     */
    protected void checkRemoteVersion(final ICheckVersionCallback callback) {

        VersionCheckRequest request = new VersionCheckRequest();
        RequestHead head = new RequestHead();

        head.setOsType("4");

        request.setHead(head);

        NetTask netTask = new NetTask(getConfig().getServer() + NetConstant.URL_VERSION_CHECK,
                request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                VersionCheckResponse response = VersionCheckResponse.getVersionCheckResponse(result);
                int remoteVersion = response.getBody().getAppVersion();
                String url = response.getBody().getUrl();
                int isForce = response.getBody().getIsForce().equals("0") ? 0 : 1;
                String description = response.getBody().getDescription();
                callback.onCheckVersion(remoteVersion, url, isForce, description);
            }
        };

        addBackGroundTask(netTask);
    }

    /**
     * 检测完版本更新后处理方法
     */
    public interface ICheckVersionCallback {
        public void onCheckVersion(int remoteVersion, String url, int isForce, String description);
    }


    /**
     * 退出登录
     */
    protected void logout() {

        clearUserInfo();

        //启动
        Intent intent = new Intent(BaseActivity.this, HomePageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

//        NetTask netTask = new NetTask(getConfig().getServer() + NetConstant.LOGOUT,
//                getLogoutRequest()) {
//            @Override
//            protected void onResponse(NetTask task, String result) {
//                clearUserInfo();
//
//                //启动
//                Intent intent = new Intent(BaseActivity.this, HomePageActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//
//            @Override
//            protected void onFailed(NetTask task, String errorCode, String errorMsg) {
//            }
//        };
//
//        addTask(netTask);
    }

    /**
     * 清除登陆信息
     */
    public void clearUserInfo() {
        getConfig().setUserId("");
    }

    /**
     * 退出登录请求bean
     */
    private RequestBean getLogoutRequest() {
        VersionCheckRequest request = new VersionCheckRequest();
        RequestHead head = new RequestHead();

        head.setAccessToken(getConfig().getToken());
        head.setUserId(getConfig().getUserId());

        request.setHead(head);

        return request;
    }

    /**
     * 运行时权限申请 --------------------------------------------------------------------------
     */

    private PermissionListener permissionListener;


    public interface PermissionListener {

        void onGranted();

        void onDenied(List<String> permission);
    }


    public void requestRunTimePermission(String[] permissions, PermissionListener listener) {

        permissionListener = listener;

        List<String> permissionList = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (permissionList.isEmpty()) {
            listener.onGranted();
        } else {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 101:
                if (grantResults.length > 0) {

                    List<String> deniedPermissions = new ArrayList<>();

                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        permissionListener.onGranted();
                    } else {
                        permissionListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }
}
