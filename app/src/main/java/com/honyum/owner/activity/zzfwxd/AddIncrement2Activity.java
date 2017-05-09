package com.honyum.owner.activity.zzfwxd;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.activity.common.PaymentActivity;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.data.AddIncrementReqBody;
import com.honyum.owner.data.IncrementType;
import com.honyum.owner.net.AddIncrementRequest;
import com.honyum.owner.net.PayUrlResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;

public class AddIncrement2Activity extends BaseActivity {

    private int frequency = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mt_order2);

        initView();
    }

    private void initView() {
        final IncrementType mtInfo = (IncrementType) getIntent().getSerializableExtra("data");
        ((TextView) findViewById(R.id.tv_mt_type)).setText(mtInfo.getName() + " ￥" + mtInfo.getPrice());
        ((TextView) findViewById(R.id.tv_mt_content)).setText("说明：" + mtInfo.getContent() + "");
        ((TextView) findViewById(R.id.tv_lift_brand)).setText(getConfig().getBrand());
        ((TextView) findViewById(R.id.tv_lift_model)).setText(getConfig().getModel());
        ((TextView) findViewById(R.id.tv_owner_name)).setText(getConfig().getName());
        ((TextView) findViewById(R.id.tv_owner_tel)).setText(getConfig().getTel());
        ((TextView) findViewById(R.id.tv_sel_address)).setText(getConfig().getAddress());

        final TextView tvWbjg = (TextView) findViewById(R.id.wbjg);
        final TextView tvCount = (TextView) findViewById(R.id.tv_count);

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

        String server = getConfig().getServer() + NetConstant.ADD_INCREMENT;
        AddIncrementRequest request = new AddIncrementRequest();
        RequestHead head = new RequestHead();
        AddIncrementReqBody body = new AddIncrementReqBody();

        head.setAccessToken(getConfig().getToken());
        head.setUserId(getConfig().getUserId());

        body.setIncrementTypeId(mtTypeId);
        body.setFrequency(frequency);

        request.setHead(head);
        request.setBody(body);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                PayUrlResponse.PayUrlResBody resBody = PayUrlResponse.getResult(result);
                Intent intent = new Intent(AddIncrement2Activity.this, PaymentActivity.class);
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
