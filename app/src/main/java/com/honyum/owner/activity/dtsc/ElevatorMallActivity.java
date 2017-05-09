package com.honyum.owner.activity.dtsc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;


public class ElevatorMallActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevator_mall);

        initView();
    }

    private void initView() {
        initTitleBar(R.id.title, "电梯商城", R.mipmap.back, backClickListener, 0, null);

        findViewById(R.id.ll_ztxs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ElevatorMallActivity.this, EMztxsActivity.class));
            }
        });

        findViewById(R.id.ll_dtpj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ElevatorMallActivity.this, EMdtpjActivity.class));
            }
        });

        findViewById(R.id.ll_dtzh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ElevatorMallActivity.this, EMdtzhActivity.class));
            }
        });
    }
}
