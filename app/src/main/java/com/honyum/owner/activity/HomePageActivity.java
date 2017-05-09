package com.honyum.owner.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.fragment.BusinessFragment;
import com.honyum.owner.fragment.MyCenterFragment;

public class HomePageActivity extends BaseActivity {

    private FragmentManager fragmentManager;

    private FragmentTransaction fragmentTransaction;

    private BusinessFragment businessFragment;

    private MyCenterFragment myCenterFragment;

    private Fragment preFragment;

    private ImageView preIv;

    private TextView preTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initTitleBar(R.id.title, getString(R.string.app_name), 0, null, 0, null);

        initFragment();
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();

        initBusinessFragment();

        findViewById(R.id.ll_business).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBusinessFragment();
            }
        });

        findViewById(R.id.ll_my).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMyCenterFragment();
            }
        });
    }

    private void initMyCenterFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();

        if (myCenterFragment == null) {
            myCenterFragment = new MyCenterFragment();
            fragmentTransaction.add(R.id.frameLayout, myCenterFragment);
        }

        if (preFragment != null) {
            fragmentTransaction.hide(preFragment);
        }

        fragmentTransaction.show(myCenterFragment);
        fragmentTransaction.commit();

        if (preIv != null && preTv != null) {
            preIv.setSelected(false);
            preTv.setSelected(false);
        }

        preIv = (ImageView) findViewById(R.id.iv_my);
        preIv.setSelected(true);

        preTv = (TextView) findViewById(R.id.tv_my);
        preTv.setSelected(true);

        preFragment = myCenterFragment;
    }

    private void initBusinessFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();

        if (businessFragment == null) {
            businessFragment = new BusinessFragment();
            fragmentTransaction.add(R.id.frameLayout, businessFragment);
        }

        if (preFragment != null) {
            fragmentTransaction.hide(preFragment);
        }

        fragmentTransaction.show(businessFragment);
        fragmentTransaction.commit();

        if (preIv != null && preTv != null) {
            preIv.setSelected(false);
            preTv.setSelected(false);
        }

        preIv = (ImageView) findViewById(R.id.iv_business);
        preIv.setSelected(true);

        preTv = (TextView) findViewById(R.id.tv_business);
        preTv.setSelected(true);

        preFragment = businessFragment;
    }
}
