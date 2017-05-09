package com.honyum.owner.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.data.MtTaskInfo;
import com.honyum.owner.data.RepairTaskInfo;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.utils.Utils;

public class TaskDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        initTitleBar(R.id.title, "任务详情", R.mipmap.back, backClickListener, 0, null);

        initView();
    }

    private void initView() {
        int taskType = getIntent().getIntExtra("task_type", -1);

        if (taskType == 0) {
            final MtTaskInfo info = (MtTaskInfo) getIntent().getSerializableExtra("data");

            findViewById(R.id.ll_mt_task_info).setVisibility(View.VISIBLE);
            findViewById(R.id.ll_rp_task_info).setVisibility(View.GONE);

            ((TextView) findViewById(R.id.tv_mt_task_code)).setText(info.getTaskCode());
            ((TextView) findViewById(R.id.tv_mt_type)).setText(info.getMaintOrderInfo().getMaintypeInfo().getName());
            ((TextView) findViewById(R.id.tv_mt_plan_time)).setText(info.getPlanTime());
            ((TextView) findViewById(R.id.tv_mt_worker_name)).setText(info.getMaintUserInfo().getName());
            ((TextView) findViewById(R.id.tv_mt_worker_tel)).setText(info.getMaintUserInfo().getTel());

            int state = Utils.getInt(info.getState());

            final Button btnConfirm = (Button) findViewById(R.id.btn_confirm);

            if (state >= 1) {
                btnConfirm.setEnabled(false);
                btnConfirm.setText("已确认");
            } else {
                btnConfirm.setEnabled(true);
                btnConfirm.setText("确认");
            }

            if (state == 2) {
                findViewById(R.id.btn_rating).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_rating).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TaskDetailActivity.this, RatingActivity.class);
                        intent.putExtra("rating_type", 0);
                        intent.putExtra("mt_task_id", info.getId());
                        startActivityForResult(intent, 101);
                    }
                });
            }

            if (state == 3) {
                findViewById(R.id.btn_confirm).setVisibility(View.GONE);
                findViewById(R.id.ll_my_rating).setVisibility(View.VISIBLE);

                RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
                ratingBar.setRating(info.getEvaluateResult());
                ((TextView) findViewById(R.id.tv_rating_describe)).setText(info.getEvaluateContent());
            }

            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmMtTask(info.getId(), btnConfirm);
                }
            });
        } else {
            RepairTaskInfo info = (RepairTaskInfo) getIntent().getSerializableExtra("data");
            findViewById(R.id.ll_mt_task_info).setVisibility(View.GONE);
            findViewById(R.id.ll_rp_task_info).setVisibility(View.VISIBLE);

            ((TextView) findViewById(R.id.tv_rp_task_code)).setText(info.getCode());
            ((TextView) findViewById(R.id.tv_rp_task_state)).setText(info.getStateStr());
            ((TextView) findViewById(R.id.tv_rp_worker_name)).setText(info.getWorkerName());
            ((TextView) findViewById(R.id.tv_rp_worker_tel)).setText(info.getWorkerTel());

            int state = Utils.getInt(info.getState());

            if (state >= 5) {
                findViewById(R.id.ll_rp_task_describe).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.tv_rp_task_describe)).setText(info.getFinishResult());
            }
        }
    }

    private void confirmMtTask(String id, final Button btnConfirm) {

        if (Utils.isEmpty(id)) {
            return;
        }

        String server = getConfig().getServer() + NetConstant.CONFIRM_MT_TASK;
        String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{" +
                "\"maintOrderPorcessId\":\"" + id + "\"}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                btnConfirm.setEnabled(false);
                btnConfirm.setText("已确认");
            }
        };

        addTask(netTask);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {
            int ratingStar = data.getIntExtra("rating_star", -1);
            String ratingContent = data.getStringExtra("rating_content");

            findViewById(R.id.ll_my_rating).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_confirm).setVisibility(View.GONE);
            findViewById(R.id.btn_rating).setVisibility(View.GONE);

            RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
            ratingBar.setRating(ratingStar);
            ((TextView) findViewById(R.id.tv_rating_describe)).setText(ratingContent);
        }
    }
}
