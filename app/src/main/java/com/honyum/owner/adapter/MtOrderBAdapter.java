package com.honyum.owner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.honyum.owner.R;
import com.honyum.owner.data.MtOrderInfo;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/3/1 0001
 */

public class MtOrderBAdapter extends BaseQuickAdapter<MtOrderInfo> {

    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(MtOrderBAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onItemClick(MtOrderInfo info);
    }

    private onListViewListener onListViewListener;

    public void setOnListViewListener(MtOrderBAdapter.onListViewListener onListViewListener) {
        this.onListViewListener = onListViewListener;
    }

    public interface onListViewListener {
        void onLvListener(RecyclerView listView, MtOrderInfo info);
    }

    public MtOrderBAdapter(List<MtOrderInfo> data) {
        super(R.layout.layout_mt_order_item, data);
    }

    @Override
    protected void convert(BaseViewHolder vh, final MtOrderInfo mtOrderInfo) {

        vh.setText(R.id.wblx, mtOrderInfo.getMainttypeName())
                .setText(R.id.wbnr, "1".equals(mtOrderInfo.getMaintypeInfo().getId())
                        ? "剩余次数:" + mtOrderInfo.getFrequency() : "到期时间:" + mtOrderInfo.getExpireTime());

        vh.addOnClickListener(R.id.wbxq)
                .addOnClickListener(R.id.xf)
                .addOnClickListener(R.id.lxkf)
                .addOnClickListener(R.id.tv_ckdd);

        if ("1".equals(mtOrderInfo.getMaintypeInfo().getId()))
            vh.setImageResource(R.id.iv_bg, R.mipmap.icon_level_3);
        else if ("2".equals(mtOrderInfo.getMaintypeInfo().getId()))
            vh.setImageResource(R.id.iv_bg, R.mipmap.icon_level_2);
        else if ("3".equals(mtOrderInfo.getMaintypeInfo().getId()))
            vh.setImageResource(R.id.iv_bg, R.mipmap.icon_level_1);

        if (onListViewListener != null)
            onListViewListener.onLvListener((RecyclerView) vh.getView(R.id.listView), mtOrderInfo);

        vh.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(mtOrderInfo);
                }
            }
        });
    }
}