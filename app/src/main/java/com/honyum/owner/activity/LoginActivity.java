package com.honyum.owner.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.honyum.owner.R;
import com.honyum.owner.activity.common.RegisterActivity;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.net.LoginRequest;
import com.honyum.owner.net.LoginResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

//        if ("".equals(getConfig().getUserId())) {
//            initView();
//        } else {
//            jumpToActivity();
//        }
    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.iv_logo).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                showSelIpDialog();
                return true;
            }
        });

        findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        final EditText etUserName = (EditText) findViewById(R.id.et_user_name);
        final EditText etPassword = (EditText) findViewById(R.id.et_password);

        etUserName.setText(getConfig().getLoginUserName());

        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(etUserName.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    private void showSelIpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] ips = {"211.147.152.6", "123.57.10.16", "192.168.0.81", "192.168.0.82"};
        builder.setItems(ips, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getConfig().setIp(ips[which]);
            }
        });
        builder.create().show();
    }

    private void login(final String userName, final String pwd) {

        if (Utils.isEmpty(userName)) {
            showToast("请输入用户名!");
            return;
        }

        if (Utils.isEmpty(pwd)) {
            showToast("请输入密码!");
            return;
        }

        String server = getConfig().getServer() + NetConstant.LOGIN;

        LoginRequest request = new LoginRequest();
        RequestHead head = new RequestHead();
        LoginRequest.LoginReqBody body = request.new LoginReqBody();

        body.setTel(userName);
        body.setPassword(Utils.encryptMD5(pwd));

        request.setBody(body);
        request.setHead(head);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                LoginResponse response = LoginResponse.getResult(result);
                getConfig().setToken(response.getHead().getAccessToken());

                getConfig().setLoginUserName(userName);

                getConfig().setUserId(response.getBody().getUserId());
                getConfig().setUserName(response.getBody().getUserName());
                getConfig().setName(response.getBody().getName());
                getConfig().setTel(response.getBody().getTel());
                getConfig().setBrand(response.getBody().getBrand());
                getConfig().setModel(response.getBody().getModel());
                getConfig().setCellName(response.getBody().getCellName());
                getConfig().setAddress(response.getBody().getAddress());
                getConfig().setLat(response.getBody().getLat());
                getConfig().setLng(response.getBody().getLng());

                finish();

                registerJPush(response.getHead().getAccessToken());

//                jumpToActivity();
            }

            @Override
            protected void onFailed(NetTask task, String errorCode, String errorMsg) {
                super.onFailed(task, errorCode, errorMsg);
//                initView();
            }
        };

        addTask(netTask);
    }

    private void jumpToActivity() {
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
        finish();
    }
}
