package com.honyum.owner.activity.common;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.net.EditPwdRequest;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;

public class EditPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        initView();
    }

    private void initView() {
        initTitleBar(R.id.title, "修改密码", R.mipmap.back, backClickListener, 0, null);

        final EditText etOldPwd = (EditText) findViewById(R.id.et_old);

        final EditText etNewPwd1 = (EditText) findViewById(R.id.et_new);

        final EditText etNewPwd2 = (EditText) findViewById(R.id.et_new_confirm);

        findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitEdit(etOldPwd.getText().toString(),
                        etNewPwd1.getText().toString(), etNewPwd2.getText().toString());
            }
        });
    }

    private void submitEdit(String oldPwd, String newPwd1, String newPwd2) {

        if (Utils.isEmpty(oldPwd) || Utils.isEmpty(newPwd1) || Utils.isEmpty(newPwd2)) {
            showToast("填写内容不能为空");
            return;
        }

        if (!newPwd1.equals(newPwd2)) {
            showToast("两次输入的新密码不一致");
            return;
        }

        String server = getConfig().getServer() + NetConstant.EDIT_PASSWORD;

        EditPwdRequest request = new EditPwdRequest();
        RequestHead head = new RequestHead();
        EditPwdRequest.EditPwdReqBody body = request.new EditPwdReqBody();

        head.setAccessToken(getConfig().getToken());
        head.setUserId(getConfig().getUserId());

        body.setOldPwd(Utils.encryptMD5(oldPwd));
        body.setNewPwd(Utils.encryptMD5(newPwd2));

        request.setHead(head);
        request.setBody(body);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                showToast("密码修改成功");
                onBackPressed();
            }
        };

        addTask(netTask);
    }
}
