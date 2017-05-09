package com.honyum.owner.activity.wbdd;

import android.os.Bundle;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;

public class MtDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mt_detail);

        initView();
    }

    private void initView() {
        String data = getIntent().getStringExtra("data");

        initTitleBar(R.id.title, "服务详情", R.mipmap.back, backClickListener, 0, null);

        ((TextView) findViewById(R.id.wbxq)).setText(data);
    }
}
