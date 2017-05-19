package com.honyum.owner.activity.common;

import android.os.Bundle;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;

public class AboutActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initTitleBar();
    }

    private void initTitleBar() {
        initTitleBar(R.id.title_about, "关于", R.mipmap.back, backClickListener, 0, null);
    }


}
