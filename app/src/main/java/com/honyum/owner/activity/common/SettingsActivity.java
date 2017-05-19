package com.honyum.owner.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.honyum.owner.R;
import com.honyum.owner.activity.LoginActivity;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.utils.Utils;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();
    }

    private void initView() {
        initTitleBar(R.id.title, "设置", R.mipmap.back, backClickListener, 0, null);

        findViewById(R.id.fl_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isLogin()) {
                    showToast("您需要登录才能修改密码!");
                    return;
                }
                Intent intent = new Intent(SettingsActivity.this, EditPasswordActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.fl_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRemoteVersion(new ICheckVersionCallback() {
                    @Override
                    public void onCheckVersion(int remoteVersion, String url, int isForce, String description) {
                        updateApk(remoteVersion, url, isForce, description);
                    }
                });
            }
        });

        findViewById(R.id.fl_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
            }
        });
    }

    /**
     * 更新APK
     *
     * @param remoteVersion 请求的最新版本号
     * @param url           下载链接
     * @param isForce       是否强制更新
     * @param description   更新描述
     */
    private void updateApk(int remoteVersion, String url, int isForce, String description) {

        //当没有下载url时，不再进行版本的检测
        if (Utils.isEmpty(url)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        if (Utils.isEmpty(description)) {
            description = "有新的版本可用";
        }

        int curVersion = Utils.getVersionCode(this);
        if (remoteVersion > curVersion) {
            updateApk(url, isForce, description, new IUpdateCancelCallback() {
                @Override
                public void onCancel() {
                }
            });
        } else {
            showToast("当前已经是最新版本!");
        }
    }
}
