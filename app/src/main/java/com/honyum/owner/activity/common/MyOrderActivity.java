package com.honyum.owner.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.adapter.MtOrderBAdapter;
import com.honyum.owner.adapter.RepairOrderBAdapter;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.data.MtOrderInfo;
import com.honyum.owner.data.RepairOrderInfo;
import com.honyum.owner.net.MyMtOrderResponse;
import com.honyum.owner.net.MyRepairOrderResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;

public class MyOrderActivity extends BaseActivity {

    private static final int SEL_MT_ORDER = 0;
    private static final int SEL_REPAIR_ORDER = 1;

    private int itemType = SEL_MT_ORDER;

    private TextView preTv;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        initTitleBar(R.id.title, "我的订单", R.mipmap.back, backClickListener, 0, null);

        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initMtOrder();

        findViewById(R.id.tv_mt_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemType = SEL_MT_ORDER;
                initMtOrder();
            }
        });

        findViewById(R.id.tv_repair_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemType = SEL_REPAIR_ORDER;
                initRpOrder();
            }
        });
    }

    private void initRpOrder() {
        if (preTv != null) {
            preTv.setSelected(false);
        }
        preTv = (TextView) findViewById(R.id.tv_repair_order);
        preTv.setSelected(true);

        requestRepairOrder();
    }

    private void requestRepairOrder() {
        String server = getConfig().getServer() + NetConstant.GET_REPAIR_ORDER_LIST;
        String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                MyRepairOrderResponse response = MyRepairOrderResponse.getResult(result);
                RepairOrderBAdapter adapter = new RepairOrderBAdapter(response.getBody());
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new RepairOrderBAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(RepairOrderInfo info) {
                        Intent intent = new Intent(MyOrderActivity.this, OrderDetailActivity.class);
                        intent.putExtra("order_type", 1);
                        intent.putExtra("data", info);
                        startActivityForResult(intent, SEL_REPAIR_ORDER);
                    }
                });
            }
        };

        addTask(netTask);
    }

    private void initMtOrder() {
        if (preTv != null) {
            preTv.setSelected(false);
        }
        preTv = (TextView) findViewById(R.id.tv_mt_order);
        preTv.setSelected(true);

        requestMtOrder();
    }

    private void requestMtOrder() {
        String server = getConfig().getServer() + NetConstant.GET_MT_ORDER_LIST;
        String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                MyMtOrderResponse response = MyMtOrderResponse.getResult(result);
                MtOrderBAdapter adapter = new MtOrderBAdapter(response.getBody());
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new MtOrderBAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(MtOrderInfo info) {
                        Intent intent = new Intent(MyOrderActivity.this, OrderDetailActivity.class);
                        intent.putExtra("order_type", 0);
                        intent.putExtra("data", info);
                        startActivityForResult(intent, SEL_MT_ORDER);
                    }
                });
            }
        };

        addTask(netTask);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEL_MT_ORDER) {
            initMtOrder();
        } else {
            initRpOrder();
        }
    }
}
