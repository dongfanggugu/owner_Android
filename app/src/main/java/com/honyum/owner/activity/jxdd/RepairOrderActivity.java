package com.honyum.owner.activity.jxdd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.honyum.owner.R;
import com.honyum.owner.activity.common.OrderDetailActivity;
import com.honyum.owner.adapter.RepairOrderBAdapter;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.data.RepairOrderInfo;
import com.honyum.owner.net.MyRepairOrderResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;

public class RepairOrderActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_order);

        initTitleBar(R.id.title, "快修订单", R.mipmap.back, backClickListener, 0, null);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestRepairOrder();
    }

    private void requestRepairOrder() {
        String server = getConfig().getServer() + NetConstant.GET_REPAIR_ORDER_LIST;
        String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                swipeRefreshLayout.setRefreshing(false);
                MyRepairOrderResponse response = MyRepairOrderResponse.getResult(result);
                RepairOrderBAdapter adapter = new RepairOrderBAdapter(response.getBody());
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new RepairOrderBAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(RepairOrderInfo info) {
                        Intent intent = new Intent(RepairOrderActivity.this, OrderDetailActivity.class);
                        intent.putExtra("order_type", 1);
                        intent.putExtra("data", info);
                        startActivity(intent);
                    }
                });
            }
        };

        addBackGroundTask(netTask);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.title_bar, R.color.colorPrimary, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestRepairOrder();
            }
        });
    }
}
