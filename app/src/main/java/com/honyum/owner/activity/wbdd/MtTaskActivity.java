package com.honyum.owner.activity.wbdd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.honyum.owner.R;
import com.honyum.owner.activity.common.TaskDetailActivity;
import com.honyum.owner.adapter.MtTaskAdapter;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.data.MtTaskInfo;
import com.honyum.owner.net.MtTaskResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;

public class MtTaskActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mt_task);

        initTitleBar(R.id.title, "维保任务", R.mipmap.back, backClickListener, 0, null);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestMtTask();
    }

    private void requestMtTask() {
        String mtOrderId = getIntent().getStringExtra("mt_order_id");

        String server = getConfig().getServer() + NetConstant.GET_MT_PROCESS_ORDER_LIST;
        String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{" +
                "\"maintOrderId\":\"" + mtOrderId + "\"}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                MtTaskResponse response = MtTaskResponse.getResult(result);
                MtTaskAdapter adapter = new MtTaskAdapter(response.getBody());
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new MtTaskAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(MtTaskInfo info) {
                        Intent intent = new Intent(MtTaskActivity.this, TaskDetailActivity.class);
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
    }
}
