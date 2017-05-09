package com.honyum.owner.activity.wbdd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.honyum.owner.R;
import com.honyum.owner.activity.common.PaymentActivity;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.data.MtOrderOrderInfo;
import com.honyum.owner.net.MtOrderOrderResponse;
import com.honyum.owner.net.PayUrlResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;

import java.util.List;

public class MtOrderOrderActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mt_order_order);

        initTitleBar(R.id.title, "增值服务", R.mipmap.back, backClickListener, 0, null);

        initView();

        reqOrder();
    }

    private void reqOrder() {
        String incrementTypeId = getIntent().getStringExtra("incrementTypeId");

        String server = getConfig().getServer() + NetConstant.MT_ORDER_ORDER_LIST;
        final String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                MtOrderOrderResponse response = MtOrderOrderResponse.getResult(result);
                MyAdapter adapter = new MyAdapter(response.getBody());
                recyclerView.setAdapter(adapter);
            }
        };

        addTask(netTask);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private class MyAdapter extends BaseQuickAdapter<MtOrderOrderInfo> {

        public MyAdapter(List<MtOrderOrderInfo> data) {
            super(R.layout.layout_order_item, data);
        }

        @Override
        protected void convert(BaseViewHolder vh, final MtOrderOrderInfo info) {
            vh.setText(R.id.code, info.getCode())
                    .setText(R.id.time, info.getCreateTime())
                    .setText(R.id.zflx, "支付类型：" + info.getMainttypeInfo().getName())
                    .setText(R.id.zfje, "支付金额：￥" + info.getPayMoney())
                    .setText(R.id.zfzt, "支付状态：" + ("1".equals(info.getMaintOrderInfo().getIsPay()) ? "已支付" : "未支付"))
                    .setVisible(R.id.zfbtn, !"1".equals(info.getMaintOrderInfo().getIsPay()))
                    .setText(R.id.zfsj, "支付时间：" + ("1".equals(info.getMaintOrderInfo().getIsPay()) ? info.getPayTime() : "暂未支付"));

            vh.getView(R.id.zfbtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestPayment(info);
                }
            });
        }
    }

    private void requestPayment(MtOrderOrderInfo info) {
        String server = getConfig().getServer() + NetConstant.CONTINUE_PAYMENT;
        final String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{" +
                "\"paymentId\":\"" + info.getId() + "\"}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                PayUrlResponse.PayUrlResBody resBody = PayUrlResponse.getResult(result);
                Intent intent = new Intent(MtOrderOrderActivity.this, PaymentActivity.class);
                intent.putExtra("url", resBody.getUrl());
                startActivity(intent);
            }
        };

        addTask(netTask);
    }
}
