package com.honyum.owner.activity.wbxd;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.activity.common.LinkModifyActivity;
import com.honyum.owner.activity.common.PaymentActivity;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.data.AddMtOrderReqBody;
import com.honyum.owner.data.MtTypeInfo;
import com.honyum.owner.net.AddMtOrderRequest;
import com.honyum.owner.net.PayUrlResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;

public class AddMtOrder2Activity extends BaseActivity {

    private int frequency = 1;

    private TextView mTvLinkName;

    private TextView mTvLinkTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mt_order2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        final MtTypeInfo mtInfo = (MtTypeInfo) getIntent().getSerializableExtra("data");
        ((TextView) findViewById(R.id.tv_mt_type)).setText(mtInfo.getName() + " ￥" + mtInfo.getPrice());
        ((TextView) findViewById(R.id.tv_mt_content)).setText("说明：" + mtInfo.getContent() + "");
        ((TextView) findViewById(R.id.tv_lift_brand)).setText(getConfig().getBrand());
        ((TextView) findViewById(R.id.tv_lift_model)).setText(getConfig().getModel());
        ((TextView) findViewById(R.id.tv_owner_name)).setText(getConfig().getName());
        ((TextView) findViewById(R.id.tv_owner_tel)).setText(getConfig().getTel());
        ((TextView) findViewById(R.id.tv_sel_address)).setText(getConfig().getAddress());


        mTvLinkName = (TextView) findViewById(R.id.tv_link_name);
        mTvLinkTel = (TextView) findViewById(R.id.tv_link_tel);

        mTvLinkName.setText(getConfig().getLinkName());
        mTvLinkTel.setText(getConfig().getLinkTel());

        findViewById(R.id.btn_modify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddMtOrder2Activity.this, LinkModifyActivity.class));
            }
        });

        final TextView tvWbjg = (TextView) findViewById(R.id.wbjg);
        final TextView tvCount = (TextView) findViewById(R.id.tv_count);

        tvWbjg.setText("￥" + mtInfo.getPrice());

        findViewById(R.id.iv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCount.setText("" + (frequency += 1) + "");
                tvWbjg.setText("￥" + (frequency * mtInfo.getPrice()) + "");
            }
        });

        findViewById(R.id.iv_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (frequency == 1)
                    return;
                tvCount.setText("" + (frequency -= 1) + "");
                tvWbjg.setText("￥" + (frequency * mtInfo.getPrice()) + "");
            }
        });

        initTitleBar(R.id.title, "维保订单", R.mipmap.back, backClickListener,
                0, null);

        findViewById(R.id.tv_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMtOrder(mtInfo.getId());
            }
        });
    }

    private void addMtOrder(String mtTypeId) {

        if (Utils.isEmpty(mtTypeId)) {
            return;
        }

        String server = getConfig().getServer() + NetConstant.ADD_MT_ORDER;
        AddMtOrderRequest request = new AddMtOrderRequest();
        RequestHead head = new RequestHead();
        AddMtOrderReqBody body = new AddMtOrderReqBody();

        head.setAccessToken(getConfig().getToken());
        head.setUserId(getConfig().getUserId());

        body.setMainttypeId(mtTypeId);
        body.setFrequency(frequency);

        request.setHead(head);
        request.setBody(body);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                PayUrlResponse.PayUrlResBody resBody = PayUrlResponse.getResult(result);
                Intent intent = new Intent(AddMtOrder2Activity.this, PaymentActivity.class);
                intent.putExtra("url", resBody.getUrl());
                startActivity(intent);
                finish();
            }
        };

        addTask(netTask);
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("提示");
        builder.setMessage("添加维保订单成功!");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();
    }
}
