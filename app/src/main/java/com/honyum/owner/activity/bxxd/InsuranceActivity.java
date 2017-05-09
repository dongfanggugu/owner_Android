package com.honyum.owner.activity.bxxd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.net.AddGuaranteeRequest;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;

public class InsuranceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);

        initTitleBar(R.id.title, "一元大保障", R.mipmap.back, backClickListener, R.mipmap.icon_custom_service, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:4009196333"));
                startActivity(intent);
            }
        });

        initView();
    }

    private void initView() {

        ((TextView) findViewById(R.id.tv_name)).setText(getConfig().getName());
        ((TextView) findViewById(R.id.tv_tel)).setText(getConfig().getTel());
        ((TextView) findViewById(R.id.tv_address)).setText(getConfig().getAddress());

        findViewById(R.id.tv_ckbxd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InsuranceActivity.this, ElevatorInsuranceActivity.class));
            }
        });

        findViewById(R.id.tv_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGuarantee();
            }
        });
    }

    private void addGuarantee() {

        String server = getConfig().getServer() + NetConstant.ADD_GUARANTEE;

        AddGuaranteeRequest request = new AddGuaranteeRequest();
        RequestHead head = new RequestHead();
        AddGuaranteeRequest.AddGuaranteeReqBody body = request.new AddGuaranteeReqBody();

        head.setAccessToken(getConfig().getToken());
        head.setUserId(getConfig().getUserId());

        body.setName(getConfig().getName());
        body.setTel(getConfig().getTel());
        body.setAddress(getConfig().getAddress());

        request.setHead(head);
        request.setBody(body);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                showToast("已经提交您的申请,请等待相关人员与您联系");
            }
        };

        addTask(netTask);
    }
}
