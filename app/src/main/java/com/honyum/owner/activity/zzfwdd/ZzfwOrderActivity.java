package com.honyum.owner.activity.zzfwdd;

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
import com.honyum.owner.data.IncrementOrderOrderInfo;
import com.honyum.owner.net.IncrementOrderOrderListResponse;
import com.honyum.owner.net.PayUrlResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;

import java.util.List;

public class ZzfwOrderActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzfw_order);

        initTitleBar(R.id.title, "增值服务", R.mipmap.back, backClickListener, 0, null);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reqZzOrder();
    }

    private void reqZzOrder() {
        String incrementTypeId = getIntent().getStringExtra("incrementTypeId");

        String server = getConfig().getServer() + NetConstant.GET_INCREMENT_ORDER_ORDER_LIST;
        final String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{" +
                "\"incrementTypeId\":\"" + incrementTypeId + "\"}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                IncrementOrderOrderListResponse response = IncrementOrderOrderListResponse.getResult(result);
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

    private class MyAdapter extends BaseQuickAdapter<IncrementOrderOrderInfo> {

        public MyAdapter(List<IncrementOrderOrderInfo> data) {
            super(R.layout.layout_order_item, data);
        }

        @Override
        protected void convert(BaseViewHolder vh, final IncrementOrderOrderInfo info) {
            vh.setText(R.id.code, info.getCode())
                    .setText(R.id.time, info.getCreateTime())
                    .setText(R.id.zflx, "支付类型：" + info.getIncrementTypeInfo().getName())
                    .setText(R.id.zfje, "支付金额：￥" + info.getPayMoney())
                    .setText(R.id.zfzt, "支付状态：" + ("1".equals(info.getIsPay()) ? "已支付" : "未支付"))
                    .setVisible(R.id.zfbtn, !"1".equals(info.getIsPay()))
                    .setText(R.id.zfsj, "支付时间：" + ("1".equals(info.getIsPay()) ? info.getPayTime() : "暂未支付"));

            vh.getView(R.id.zfbtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestPayment(info);
                }
            });
        }
    }

    private void requestPayment(IncrementOrderOrderInfo info) {
        String server = getConfig().getServer() + NetConstant.CONTINUE_PAYMENT;
        final String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{" +
                "\"paymentId\":\"" + info.getId() + "\"}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                PayUrlResponse.PayUrlResBody resBody = PayUrlResponse.getResult(result);
                Intent intent = new Intent(ZzfwOrderActivity.this, PaymentActivity.class);
                intent.putExtra("url", resBody.getUrl());
                startActivity(intent);
            }
        };

        addTask(netTask);
    }
}
