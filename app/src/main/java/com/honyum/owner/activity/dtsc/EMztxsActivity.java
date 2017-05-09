package com.honyum.owner.activity.dtsc;

import android.os.Bundle;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;

public class EMztxsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emztxs);

        initTitleBar(R.id.title, "整体销售", R.mipmap.back, backClickListener, 0, null);
    }
}
