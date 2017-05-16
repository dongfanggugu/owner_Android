package com.honyum.owner.activity.zzfwxd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.honyum.owner.R;
import com.honyum.owner.activity.wbdd.MtDetailActivity;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.base.Constant;
import com.honyum.owner.data.IncrementType;
import com.honyum.owner.net.GetIncrementTypeListResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.utils.Utils;

import java.util.List;

public class ValueAddedServiceActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_added_service);

        initTitleBar(R.id.title, "增值服务", R.mipmap.back, backClickListener, 0, null);

        initView();

        reqValueAddService();

    }

    private void reqValueAddService() {

        String server = getConfig().getServer() + NetConstant.GET_INCREMENT_LIST;

        String request = Constant.EMPTY_REQUEST;

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                GetIncrementTypeListResponse response = GetIncrementTypeListResponse.getResult(result);
                List<IncrementType> body = response.getBody();
                MyAdapter myAdapter = new MyAdapter(body);
                recyclerView.setAdapter(myAdapter);
            }
        };

        addTask(netTask);
    }

    private void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int i) {

                if (i > 0) {
                    showToast("功能开发中,敬请期待");
                    return;
                }
                IncrementType item = (IncrementType) adapter.getItem(i);
                if (Utils.isEmpty(getConfig().getUserId())) {
                    Intent intent = new Intent(ValueAddedServiceActivity.this, AddIncrementActivity.class);
                    intent.putExtra("data", item);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ValueAddedServiceActivity.this, AddIncrement2Activity.class);
                    intent.putExtra("data", item);
                    startActivity(intent);
                }
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                IncrementType item = (IncrementType) adapter.getItem(i);
                Intent intent = new Intent(ValueAddedServiceActivity.this, MtDetailActivity.class);
                intent.putExtra("data", item.getContent());
                startActivity(intent);
            }
        });
    }

    private class MyAdapter extends BaseQuickAdapter<IncrementType> {

        public MyAdapter(List<IncrementType> data) {
            super(R.layout.layout_maintenance_item, data);
        }

        @Override
        protected void convert(BaseViewHolder vh, IncrementType it) {

            vh.addOnClickListener(R.id.wbxq);

            vh.setText(R.id.wblx, it.getName())
                    .setText(R.id.wbnr, it.getContent())
                    .setText(R.id.wbjg, "￥" + it.getPrice());

            Glide.with(ValueAddedServiceActivity.this).load(it.getLogo()).into((ImageView) vh.getView(R.id.iv_bg));
        }
    }
}
