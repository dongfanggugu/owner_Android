package com.honyum.owner.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.honyum.owner.R;
import com.honyum.owner.activity.HomePageActivity;
import com.honyum.owner.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageView iv = (ImageView) findViewById(R.id.iv_welcome);
        Glide.with(this).load(R.mipmap.welcome_page_gif)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new GlideDrawableImageViewTarget(iv, 1));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, HomePageActivity.class));
                finish();
            }
        }, 4000);
    }
}
