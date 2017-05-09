package com.honyum.owner.activity.zzfwdd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.honyum.owner.R;
import com.honyum.owner.activity.wbdd.MtDetailActivity;
import com.honyum.owner.activity.zzfwxd.AddIncrement2Activity;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.data.IncrementOrderInfo;
import com.honyum.owner.net.IncrementOrderListResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;

import java.util.List;

public class ZzfwActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzfw);

        initTitleBar(R.id.title, "增值服务", R.mipmap.back, backClickListener, "查看历史",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ZzfwActivity.this, ZzfwHistoryOrderActivity.class));
                    }
                });

        initView();

        reqZzOrder();
    }

    private void reqZzOrder() {
        String server = getConfig().getServer() + NetConstant.GET_INCREMENT_ORDER_LIST;
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

        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter adapter, View view, int i) {

                IncrementOrderInfo info = (IncrementOrderInfo) adapter.getItem(i);

                switch (view.getId()) {
                    case R.id.wbxq:
                        Intent intent = new Intent(ZzfwActivity.this, MtDetailActivity.class);
                        intent.putExtra("data", info.getIncrementTypeInfo().getContent());
                        startActivity(intent);
                        break;
                    case R.id.xf:
                        Intent intent1 = new Intent(ZzfwActivity.this, AddIncrement2Activity.class);
                        intent1.putExtra("data", info.getIncrementTypeInfo());
                        startActivity(intent1);
                        break;
                    case R.id.lxkf:
                        Intent intent2 = new Intent(Intent.ACTION_DIAL);
                        intent2.setData(Uri.parse("tel:4009196333"));
                        startActivity(intent2);
                        break;
                    case R.id.tv_ckdd:
                        Intent intent3 = new Intent(ZzfwActivity.this, ZzfwOrderActivity.class);
                        intent3.putExtra("incrementTypeId", info.getIncrementTypeId());
                        startActivity(intent3);
                        break;
                }
            }
        });
    }

    private class MyAdapter extends BaseQuickAdapter<IncrementOrderInfo> {

        public MyAdapter(List<IncrementOrderInfo> data) {
            super(R.layout.layout_zzfw_order_item, data);
        }

        @Override
        protected void convert(BaseViewHolder vh, IncrementOrderInfo info) {
            vh.setText(R.id.wblx, info.getIncrementTypeInfo().getName());

            vh.addOnClickListener(R.id.wbxq)
                    .addOnClickListener(R.id.xf)
                    .addOnClickListener(R.id.lxkf)
                    .addOnClickListener(R.id.tv_ckdd);

            Glide.with(ZzfwActivity.this).load(info.getIncrementTypeInfo().getLogo()).into((ImageView) vh.getView(R.id.iv_bg));
        }
    }
}
