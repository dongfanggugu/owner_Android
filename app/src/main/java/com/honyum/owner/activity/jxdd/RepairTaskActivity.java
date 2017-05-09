package com.honyum.owner.activity.jxdd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.honyum.owner.R;
import com.honyum.owner.activity.common.TaskDetailActivity;
import com.honyum.owner.adapter.RepairTaskAdapter;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.data.RepairTaskInfo;
import com.honyum.owner.net.RepairTaskResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;

public class RepairTaskActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repaier_task);

        initView();

        requestRepairTask();
    }

    private void requestRepairTask() {
        String repairOrderId = getIntent().getStringExtra("repair_order_id");

        String server = getConfig().getServer() + NetConstant.GET_REPAIR_TASK_LIST;
        String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{" +
                "\"repairOrderId\":\"" + repairOrderId + "\"}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                RepairTaskResponse response = RepairTaskResponse.getResult(result);
                RepairTaskAdapter adapter = new RepairTaskAdapter(response.getBody());
                recyclerView.setAdapter(adapter);
            }
        };

        addTask(netTask);
    }

    private void initView() {
        initTitleBar(R.id.title, "维修任务", R.mipmap.back, backClickListener, 0, null);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int i) {
                RepairTaskInfo info = (RepairTaskInfo) adapter.getItem(i);
                Intent intent = new Intent(RepairTaskActivity.this, TaskDetailActivity.class);
                intent.putExtra("task_type", 1);
                intent.putExtra("data", info);
                startActivity(intent);
            }
        });
    }
}
