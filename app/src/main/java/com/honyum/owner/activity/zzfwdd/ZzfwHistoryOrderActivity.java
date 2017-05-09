package com.honyum.owner.activity.zzfwdd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.data.IncrementOrderInfo;
import com.honyum.owner.net.IncrementOrderListResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;

import java.util.List;

public class ZzfwHistoryOrderActivity extends BaseActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzfw_history_order);

        initTitleBar(R.id.title, "服务历史", R.mipmap.back, backClickListener, 0, null);

        initView();

        reqHistoryOrder();
    }

    private void reqHistoryOrder() {
        String server = getConfig().getServer() + NetConstant.GET_INCREMENT_HISTORY_ORDER_LIST;
        String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                IncrementOrderListResponse response = IncrementOrderListResponse.getResult(result);
                MyAdapter adapter = new MyAdapter(response.getBody());
                recyclerView.setAdapter(adapter);
            }
        };

        addTask(netTask);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private class MyAdapter extends BaseQuickAdapter<IncrementOrderInfo> {

        public MyAdapter(List<IncrementOrderInfo> data) {
            super(R.layout.layout_zzfw_history_item, data);
        }

        @Override
        protected void convert(BaseViewHolder vh, final IncrementOrderInfo info) {

            Glide.with(ZzfwHistoryOrderActivity.this).load(info.getIncrementTypeInfo().getLogo()).into((ImageView) vh.getView(R.id.iv_bg));

            vh.setText(R.id.wblx, info.getIncrementTypeInfo().getName())
                    .setText(R.id.wbnr, info.getIncrementTypeInfo().getContent())
                    .setText(R.id.xdsj, info.getCreateTime());

            vh.getView(R.id.ckdd).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ZzfwHistoryOrderActivity.this, ZzfwOrderActivity.class);
                    intent.putExtra("incrementTypeId", info.getIncrementTypeId());
                    startActivity(intent);
                }
            });
        }
    }
}
