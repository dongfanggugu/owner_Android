package com.honyum.owner.activity.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.net.EditPersonInfoRequest;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;

public class LinkModifyActivity extends BaseActivity {

    private EditText mEtLinkName;

    private EditText mEtLinkTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_modify);

        initTitleBar(R.id.title, "联系人修改", R.mipmap.back, backClickListener,
                0, null);
        initView();
    }


    private void initView() {

        mEtLinkName = (EditText) findViewById(R.id.et_link_name);
        mEtLinkTel = (EditText) findViewById(R.id.et_link_tel);

        mEtLinkName.setText(getConfig().getLinkName());
        mEtLinkTel.setText(getConfig().getLinkTel());

        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(mEtLinkName.getText().toString())
                        || Utils.isEmpty(mEtLinkTel.getText().toString())) {
                    showToast("请正确填写联系人信息!");
                    return;
                }
                submit(mEtLinkName.getText().toString(), mEtLinkTel.getText().toString());
            }
        });
    }


    private void submit(final String name, final String tel) {

        String server = getConfig().getServer() + NetConstant.EDIT_PERSON_INFO;

        EditPersonInfoRequest request = new EditPersonInfoRequest();
        RequestHead head = new RequestHead();
        EditPersonInfoRequest.EditPersonInfoReqBody body
                = request.new EditPersonInfoReqBody();

        head.setAccessToken(getConfig().getToken());
        head.setUserId(getConfig().getUserId());

        body.setContacts(name);
        body.setContactsTel(tel);

        request.setHead(head);
        request.setBody(body);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                showToast("联系人修改成功");
                getConfig().setLinkName(name);
                getConfig().setLinkTel(tel);
                onBackPressed();
            }
        };

        addTask(netTask);
    }

}
