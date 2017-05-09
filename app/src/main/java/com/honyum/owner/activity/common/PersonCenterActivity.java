package com.honyum.owner.activity.common;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.utils.Utils;
import com.honyum.owner.view.CircleImageView;

import java.io.File;

public class PersonCenterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        initTitleBar(R.id.title, "个人中心", R.mipmap.back, backClickListener,
                0, null);

        findViewById(R.id.iv_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonCenterActivity.this, PersonInfoActivity.class);
                startActivity(intent);
            }
        });

        CircleImageView ivAvatar = (CircleImageView) findViewById(R.id.iv_avatar);
        File avatarFile = new File(Utils.getUserAvatarPath(), getConfig().getUserId() + ".jpg");
        if (avatarFile.exists()) {
            ivAvatar.setImageBitmap(BitmapFactory.decodeFile(avatarFile.getAbsolutePath()));
        } else {
            ivAvatar.setImageResource(R.mipmap.icon_avatar_default);
        }

        TextView tvName = (TextView) findViewById(R.id.tv_user_name);
        tvName.setText(getConfig().getUserName());

        TextView tvStation = (TextView) findViewById(R.id.tv_station_address);
        tvStation.setText(getConfig().getAddress());

        findViewById(R.id.ll_cell_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonCenterActivity.this, ShowAddressActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.ll_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonCenterActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
}
