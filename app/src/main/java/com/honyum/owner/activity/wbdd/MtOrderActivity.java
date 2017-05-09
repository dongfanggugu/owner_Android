package com.honyum.owner.activity.wbdd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.honyum.owner.R;
import com.honyum.owner.activity.common.TaskDetailActivity;
import com.honyum.owner.activity.wbxd.AddMtOrder2Activity;
import com.honyum.owner.adapter.MtOrderBAdapter;
import com.honyum.owner.adapter.MtTaskAdapter;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.base.MyApplication;
import com.honyum.owner.data.MtOrderInfo;
import com.honyum.owner.data.MtTaskInfo;
import com.honyum.owner.net.MtTaskResponse;
import com.honyum.owner.net.MyMtOrderResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;

public class MtOrderActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mt_order);

        initTitleBar(R.id.title, "维保订单", R.mipmap.back, backClickListener,
                "查看历史", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MtOrderActivity.this, MtHistoryOrderActivity.class);
                        startActivity(intent);
                    }
                });

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestMtOrder();
    }

    private void requestMtOrder() {
        String server = getConfig().getServer() + NetConstant.GET_MT_ORDER_LIST;
        String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                swipeRefreshLayout.setRefreshing(false);
                MyMtOrderResponse response = MyMtOrderResponse.getResult(result);
                MtOrderBAdapter adapter = new MtOrderBAdapter(response.getBody());
                recyclerView.setAdapter(adapter);
                adapter.setOnListViewListener(new MtOrderBAdapter.onListViewListener() {
                    @Override
                    public void onLvListener(RecyclerView listView, MtOrderInfo info) {
                        requestTask(listView, info);
                    }
                });
            }
        };

        addTask(netTask);
    }

    private void requestTask(final RecyclerView listView, MtOrderInfo mtOrderInfo) {

        listView.setLayoutManager(new LinearLayoutManager(this));

        String server = MyApplication.getConfig().getServer() + NetConstant.GET_MT_PROCESS_ORDER_LIST;
        String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + MyApplication.getConfig().getToken()
                + "\",\"userId\":\"" + MyApplication.getConfig().getUserId() + "\"},\"body\":{" +
                "\"maintOrderId\":\"" + mtOrderInfo.getId() + "\"}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                MtTaskResponse response = MtTaskResponse.getResult(result);
                MtTaskAdapter adapter = new MtTaskAdapter(response.getBody());
                listView.setAdapter(adapter);
                adapter.setOnItemClickListener(new MtTaskAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(MtTaskInfo info) {
                        Intent intent = new Intent(MtOrderActivity.this, TaskDetailActivity.class);
                        intent.putExtra("task_type", 0);
                        intent.putExtra("data", info);
                        startActivityForResult(intent, 101);
                    }
                });
            }
        };

        addTask(netTask);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter adapter, View view, int i) {

                MtOrderInfo info = (MtOrderInfo) adapter.getItem(i);

                switch (view.getId()) {
                    case R.id.wbxq:
                        Intent intent = new Intent(MtOrderActivity.this, MtDetailActivity.class);
                        intent.putExtra("data", info.getMaintypeInfo().getContent());
                        startActivity(intent);
                        break;
                    case R.id.xf:
                        Intent intent1 = new Intent(MtOrderActivity.this, AddMtOrder2Activity.class);
                        intent1.putExtra("data", info.getMaintypeInfo());
                        startActivity(intent1);
                        break;
                    case R.id.lxkf:
                        Intent intent2 = new Intent(Intent.ACTION_DIAL);
                        intent2.setData(Uri.parse("tel:4009196333"));
                        startActivity(intent2);
                        break;
                    case R.id.tv_ckdd:
                        Intent intent3 = new Intent(MtOrderActivity.this, MtOrderOrderActivity.class);
                        startActivity(intent3);
                        break;
                }
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.title_bar, R.color.colorPrimary, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestMtOrder();
            }
        });
    }
}
