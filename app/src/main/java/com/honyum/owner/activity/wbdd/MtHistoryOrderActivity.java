package com.honyum.owner.activity.wbdd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.data.MtHistoryOrderInfo;
import com.honyum.owner.net.MtHistoryOrderResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;

import java.util.List;

public class MtHistoryOrderActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mt_history_order);

        initTitleBar(R.id.title, "服务历史", R.mipmap.back, backClickListener, 0, null);

        initView();

        reqHistoryOrder();
    }

    private void reqHistoryOrder() {
        String server = getConfig().getServer() + NetConstant.GET_MT_HISTORY_ORDER;
        String request = "{\"head\":{\"osType\":\"2\",\"accessToken\":\"" + getConfig().getToken()
                + "\",\"userId\":\"" + getConfig().getUserId() + "\"},\"body\":{}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                MtHistoryOrderResponse response = MtHistoryOrderResponse.getResult(result);
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

    private class MyAdapter extends BaseQuickAdapter<MtHistoryOrderInfo> {

        public MyAdapter(List<MtHistoryOrderInfo> data) {
            super(R.layout.layout_mt_history_item, data);
        }

        @Override
        protected void convert(BaseViewHolder vh, final MtHistoryOrderInfo info) {

            vh.setText(R.id.wblx, info.getMaintypeInfo().getName())
                    .setText(R.id.wbnr, info.getMaintypeInfo().getContent())
                    .setText(R.id.xdsj, info.getCreateTime());

            if ("1".endsWith(info.getMaintypeInfo().getId()))
                vh.setImageResource(R.id.iv_bg, R.mipmap.icon_level_3);
            else if ("2".equals(info.getMaintypeInfo().getId()))
                vh.setImageResource(R.id.iv_bg, R.mipmap.icon_level_2);
            else if ("3".equals(info.getMaintypeInfo().getId()))
                vh.setImageResource(R.id.iv_bg, R.mipmap.icon_level_1);

            vh.getView(R.id.ckfwd).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MtHistoryOrderActivity.this, MtTaskActivity.class);
                    intent.putExtra("mt_order_id", info.getId());
                    startActivity(intent);
                }
            });
        }
    }
}
