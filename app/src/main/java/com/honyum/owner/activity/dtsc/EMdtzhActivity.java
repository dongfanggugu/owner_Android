package com.honyum.owner.activity.dtsc;

import android.os.Bundle;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;

public class EMdtzhActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emdtzh);

        initTitleBar(R.id.title, "电梯装潢", R.mipmap.back, backClickListener, 0, null);
    }
}
