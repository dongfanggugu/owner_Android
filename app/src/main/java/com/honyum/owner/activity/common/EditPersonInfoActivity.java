package com.honyum.owner.activity.common;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.net.EditPersonInfoRequest;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;

public class EditPersonInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person_info);

        initView();
    }

    private void initView() {
        //0:修改姓名 1:修改电梯型号
        final int editType = getIntent().getIntExtra("edit_type", -1);

        final EditText et = (EditText) findViewById(R.id.et_content);

        String title = "";

        if (editType == 0) {
            title = "修改姓名";
            et.setText(getConfig().getName());
        } else if (editType == 1) {
            title = "修改电梯型号";
            et.setText(getConfig().getModel());
        }

        initTitleBar(R.id.title, title, R.mipmap.back, backClickListener,
                "提交", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        submitEdit(editType, et.getText().toString());
                    }
                });
    }

    private void submitEdit(final int editType, final String etContent) {

        String server = getConfig().getServer() + NetConstant.EDIT_PERSON_INFO;

        EditPersonInfoRequest request = new EditPersonInfoRequest();
        RequestHead head = new RequestHead();
        EditPersonInfoRequest.EditPersonInfoReqBody body
                = request.new EditPersonInfoReqBody();

        head.setAccessToken(getConfig().getToken());
        head.setUserId(getConfig().getUserId());

        if (editType == 0) {
            body.setName(etContent);
        } else if (editType == 1) {
            body.setModel(etContent);
        }

        request.setHead(head);
        request.setBody(body);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                showToast("修改成功");
                if (editType == 0) {
                    getConfig().setName(etContent);
                } else if (editType == 1) {
                    getConfig().setModel(etContent);
                }
                onBackPressed();
            }
        };

        addTask(netTask);
    }
}
