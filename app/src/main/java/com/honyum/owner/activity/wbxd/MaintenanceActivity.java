package com.honyum.owner.activity.wbxd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.honyum.owner.R;
import com.honyum.owner.activity.wbdd.MtDetailActivity;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.base.Constant;
import com.honyum.owner.data.MtTypeInfo;
import com.honyum.owner.net.MaintenanceTypeResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.utils.Utils;

import java.util.List;

public class MaintenanceActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);

        initTitleBar(R.id.title, "电梯维保", R.mipmap.back, backClickListener, 0, null);

        initView();

        requestMt();
    }

    private void requestMt() {
        String server = getConfig().getServer() + NetConstant.GET_MTTYPE_LIST;
        String request = Constant.EMPTY_REQUEST;

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                MaintenanceTypeResponse response = MaintenanceTypeResponse.getResult(result);
                MyAdapter adapter = new MyAdapter(response.getBody());

                ImageView iv = new ImageView(MaintenanceActivity.this);
                ViewGroup.LayoutParams params =
                        new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                iv.setLayoutParams(params);
                iv.setImageResource(R.mipmap.icon_maintenance_banner);

                adapter.addHeaderView(iv);

                recyclerView.setAdapter(adapter);
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
                MtTypeInfo info = (MtTypeInfo) adapter.getItem(i);
                if (Utils.isEmpty(getConfig().getUserId())) {
                    Intent intent = new Intent(MaintenanceActivity.this, AddMtOrderActivity.class);
                    intent.putExtra("data", info);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MaintenanceActivity.this, AddMtOrder2Activity.class);
                    intent.putExtra("data", info);
                    startActivity(intent);
                }
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                MtTypeInfo info = (MtTypeInfo) adapter.getItem(i);
                Intent intent = new Intent(MaintenanceActivity.this, MtDetailActivity.class);
                intent.putExtra("data", info.getContent());
                startActivity(intent);
            }
        });
    }

    private class MyAdapter extends BaseQuickAdapter<MtTypeInfo> {

        MyAdapter(List<MtTypeInfo> data) {
            super(R.layout.layout_maintenance_item, data);
        }

        @Override
        protected void convert(BaseViewHolder vh, MtTypeInfo mtTypeInfo) {

            vh.addOnClickListener(R.id.wbxq);

            vh.setText(R.id.wblx, mtTypeInfo.getName())
                    .setText(R.id.wbnr, mtTypeInfo.getContent())
                    .setText(R.id.wbjg, "￥" + mtTypeInfo.getPrice());

            if ("1".equals(mtTypeInfo.getId()))
                vh.setImageResource(R.id.iv_bg, R.mipmap.icon_level_3);
            else if ("2".equals(mtTypeInfo.getId()))
                vh.setImageResource(R.id.iv_bg, R.mipmap.icon_level_2);
            else if ("3".equals(mtTypeInfo.getId()))
                vh.setImageResource(R.id.iv_bg, R.mipmap.icon_level_1);
        }
    }
}
