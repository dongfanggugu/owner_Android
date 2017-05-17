package com.honyum.owner.activity.dtsc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;

public class MarketWebViewActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_web_view);

        initView();
    }


    private View.OnClickListener rightClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MarketWebViewActivity.this, LiftMessageActivity.class);
            intent.putExtra("type", getIntent().getStringExtra("type"));

            startActivity(intent);
        }
    };

    private void initView() {

        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        String type = getIntent().getStringExtra("type");

        String url = "";

        if (type.equals("lift")) {

            initTitleBar(R.id.title, "整梯销售", R.mipmap.back, backClickListener,
                    null, null);
            url = getConfig().getIp() + "h5/indexPage";
        } else {

            initTitleBar(R.id.title, "电梯装潢", R.mipmap.back, backClickListener,
                    null, null);

            url = getConfig().getIp() + "h5/indexelevatorDecorationPage";
        }

        View titleView = findViewById(R.id.title);

        TextView right = (TextView) titleView.findViewById(R.id.tv_right);
        right.setText("联系我们");

        right.setOnClickListener(rightClickListener);

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();

        } else {
            super.onBackPressed();
        }
    }
}
