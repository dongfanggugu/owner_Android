package com.honyum.owner.activity.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.net.ForgetRequest;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;

public class ForgetActivity extends BaseActivity {

    private EditText mEtTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        initTitleBar(R.id.title, "忘记密码", R.mipmap.back, backClickListener,
                0, null);
    }

    private void initView() {
        mEtTel = (EditText) findViewById(R.id.et_tel);

        findViewById(R.id.btn_forget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = mEtTel.getText().toString();

                if (Utils.isEmpty(tel)) {
                    showToast("请输入您注册的手机号码");
                    return;
                }


            }
        });
    }

    private void forgetSubmit(String tel) {
        ForgetRequest request = new ForgetRequest();
        ForgetRequest.ForgetReqBody body = new ForgetRequest.ForgetReqBody();

        RequestHead head = new RequestHead();

        body.setTel(tel);

        request.setHead(head);
        request.setBody(body);

        String server = getConfig() + NetConstant.URL_PWD_FORGET;
        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                showToast("密码重置成功,请及时检查您的短信通知");
                finish();
            }
        };

        addTask(netTask);
    }
}
