package com.honyum.owner.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.activity.jxdd.RepairTaskActivity;
import com.honyum.owner.activity.wbdd.MtTaskActivity;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.data.MtOrderInfo;
import com.honyum.owner.data.RepairOrderInfo;
import com.honyum.owner.utils.Utils;

public class OrderDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        initTitleBar(R.id.title, "我的订单", R.mipmap.back, backClickListener, 0, null);

        initView();
    }

    private void initView() {
        int orderType = getIntent().getIntExtra("order_type", -1);

        if (orderType == 0) {
            final MtOrderInfo info = (MtOrderInfo) getIntent().getSerializableExtra("data");
            findViewById(R.id.ll_mt_order_detail).setVisibility(View.VISIBLE);
            findViewById(R.id.ll_rp_order_detail).setVisibility(View.GONE);

            ((TextView) findViewById(R.id.tv_mt_order_code)).setText(info.getCode());
            ((TextView) findViewById(R.id.tv_mt_order_time)).setText(info.getCreateTime());
            ((TextView) findViewById(R.id.tv_mt_type)).setText(info.getMaintypeName());
            ((TextView) findViewById(R.id.tv_mt_price)).setText("￥" + info.getPrice() + "");

            findViewById(R.id.btn_look_mt_plan).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_look_mt_plan).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OrderDetailActivity.this, MtTaskActivity.class);
                    intent.putExtra("mt_order_id", info.getId());
                    startActivity(intent);
                }
            });

        } else if (orderType == 1) {
            final RepairOrderInfo info = (RepairOrderInfo) getIntent().getSerializableExtra("data");
            findViewById(R.id.ll_mt_order_detail).setVisibility(View.GONE);
            findViewById(R.id.ll_rp_order_detail).setVisibility(View.VISIBLE);

            ((TextView) findViewById(R.id.tv_rp_order_code)).setText(info.getCode());
            ((TextView) findViewById(R.id.tv_rp_order_time)).setText(info.getCreateTime());
            ((TextView) findViewById(R.id.tv_rp_state)).setText(info.getStateStr());
            ((TextView) findViewById(R.id.tv_rp_fault_type)).setText(info.getRepairTypeInfo().getName());
            ((TextView) findViewById(R.id.tv_rp_fault_describe)).setText(info.getPhenomenon());

            int state = Utils.getInt(info.getState());
            if (state >= 4) {
                findViewById(R.id.btn_look_repair_task).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_look_repair_task).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(OrderDetailActivity.this, RepairTaskActivity.class);
                        intent.putExtra("repair_order_id", info.getId());
                        startActivityForResult(intent, 101);
                    }
                });
            }

            if (8 == state) {
                findViewById(R.id.btn_rating).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_rating).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(OrderDetailActivity.this, RatingActivity.class);
                        intent.putExtra("rating_type", 1);
                        intent.putExtra("repair_order_id", info.getId());
                        startActivityForResult(intent, 101);
                    }
                });
            }

            if (9 == state) {
                findViewById(R.id.ll_my_rating).setVisibility(View.VISIBLE);

                RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
                ratingBar.setRating(Utils.getInt(info.getEvaluate()));
                ((TextView) findViewById(R.id.tv_rating_describe)).setText(info.getEvaluateInfo());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 102) {
            int ratingStar = data.getIntExtra("rating_star", -1);
            String ratingContent = data.getStringExtra("rating_content");

            findViewById(R.id.ll_my_rating).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_rating).setVisibility(View.GONE);

            RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
            ratingBar.setRating(ratingStar);
            ((TextView) findViewById(R.id.tv_rating_describe)).setText(ratingContent);
        }
    }
}
