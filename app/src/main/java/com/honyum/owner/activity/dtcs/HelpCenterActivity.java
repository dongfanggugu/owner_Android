package com.honyum.owner.activity.dtcs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;

public class HelpCenterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);

        initTitleBar(R.id.title, "帮助中心", R.mipmap.back, backClickListener,
                0, null);

        initView();
    }

    private void initView() {
        findViewById(R.id.ll_help_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpCenterActivity.this, HelpContentActivity.class);
                intent.putExtra("help", 1);
                startActivity(intent);
            }
        });

        findViewById(R.id.ll_help_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpCenterActivity.this, HelpContentActivity.class);
                intent.putExtra("help", 2);
                startActivity(intent);
            }
        });

        findViewById(R.id.ll_help_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpCenterActivity.this, HelpContentActivity.class);
                intent.putExtra("help", 3);
                startActivity(intent);
            }
        });

        findViewById(R.id.ll_help_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpCenterActivity.this, HelpContentActivity.class);
                intent.putExtra("help", 4);
                startActivity(intent);
            }
        });

        findViewById(R.id.ll_help_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpCenterActivity.this, HelpContentActivity.class);
                intent.putExtra("help", 5);
                startActivity(intent);
            }
        });

        findViewById(R.id.ll_help_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpCenterActivity.this, HelpContentActivity.class);
                intent.putExtra("help", 6);
                startActivity(intent);
            }
        });

        findViewById(R.id.ll_help_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpCenterActivity.this, HelpContentActivity.class);
                intent.putExtra("help", 7);
                startActivity(intent);
            }
        });
    }
}
