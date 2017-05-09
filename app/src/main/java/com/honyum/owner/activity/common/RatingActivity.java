package com.honyum.owner.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.net.RatingMtTaskRequest;
import com.honyum.owner.net.RatingRepairRequest;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;

public class RatingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        initView();
    }

    private void initView() {
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        final EditText etRatingDescribe = (EditText) findViewById(R.id.et_rating_describe);

        int ratingType = getIntent().getIntExtra("rating_type", -1);

        final String id;

        if (ratingType == 0) {
            id = getIntent().getStringExtra("mt_task_id");

            initTitleBar(R.id.title, "评价订单", R.mipmap.back, backClickListener, "提交", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String server = getConfig().getServer() + NetConstant.RATING_MT_TASK_ORDER;

                    RatingMtTaskRequest request = new RatingMtTaskRequest();
                    RequestHead head = new RequestHead();
                    RatingMtTaskRequest.RatingMtTaskReqBody body =
                            request.new RatingMtTaskReqBody();

                    head.setAccessToken(getConfig().getToken());
                    head.setUserId(getConfig().getUserId());

                    body.setMaintOrderProcessId(id);
                    body.setEvaluateResult((int) ratingBar.getRating());
                    body.setEvaluateContent(etRatingDescribe.getText().toString());

                    request.setHead(head);
                    request.setBody(body);

                    NetTask netTask = new NetTask(server, request) {
                        @Override
                        protected void onResponse(NetTask task, String result) {
                            showToast("评价成功!");
                            Intent intent = new Intent();
                            intent.putExtra("rating_star", (int) ratingBar.getRating());
                            intent.putExtra("rating_content", etRatingDescribe.getText().toString());
                            setResult(101, intent);
                            finish();
                        }
                    };

                    addTask(netTask);
                }
            });
        } else {
            id = getIntent().getStringExtra("repair_order_id");

            initTitleBar(R.id.title, "评价订单", R.mipmap.back, backClickListener, "提交", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String server = getConfig().getServer() + NetConstant.RATING_REPAIR_ORDER;

                    RatingRepairRequest request = new RatingRepairRequest();
                    RequestHead head = new RequestHead();
                    RatingRepairRequest.RatingRepairReqBody body = request.new RatingRepairReqBody();

                    head.setAccessToken(getConfig().getToken());
                    head.setUserId(getConfig().getUserId());

                    body.setRepairOrderId(id);
                    body.setEvaluate(((int) ratingBar.getRating()) + "");
                    body.setEvaluateInfo(etRatingDescribe.getText().toString());

                    request.setHead(head);
                    request.setBody(body);

                    NetTask netTask = new NetTask(server, request) {
                        @Override
                        protected void onResponse(NetTask task, String result) {
                            showToast("评价成功!");
                            Intent intent = new Intent();
                            intent.putExtra("rating_star", (int) ratingBar.getRating());
                            intent.putExtra("rating_content", etRatingDescribe.getText().toString());
                            setResult(102, intent);
                            finish();
                        }
                    };

                    addTask(netTask);
                }
            });
        }
    }
}