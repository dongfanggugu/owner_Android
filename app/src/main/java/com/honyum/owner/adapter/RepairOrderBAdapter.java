package com.honyum.owner.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.honyum.owner.R;
import com.honyum.owner.data.RepairOrderInfo;
import com.honyum.owner.utils.Utils;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/3/1 0001
 */

public class RepairOrderBAdapter extends BaseQuickAdapter<RepairOrderInfo> {

    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onItemClick(RepairOrderInfo info);
    }

    public RepairOrderBAdapter(List<RepairOrderInfo> data) {
        super(R.layout.layout_repair_order_item, data);
    }

    @Override
    protected void convert(BaseViewHolder vh, final RepairOrderInfo repairOrderInfo) {
        vh.setText(R.id.tv_repair_time, repairOrderInfo.getCreateTime())
                .setText(R.id.tv_repair_fault, "故障描述:" + repairOrderInfo.getPhenomenon());

        int state = Utils.getInt(repairOrderInfo.getState());

        switch (state) {
            case 1:
                vh.setText(R.id.tv_repair_state, "待确认");
                repairOrderInfo.setStateStr("待确认");
                break;
            case 2:
                vh.setText(R.id.tv_repair_state, "已确认");
                repairOrderInfo.setStateStr("已确认");
                break;
            case 4:
                vh.setText(R.id.tv_repair_state, "已委派");
                repairOrderInfo.setStateStr("已委派");
                break;
            case 6:
                vh.setText(R.id.tv_repair_state, "维修中");
                repairOrderInfo.setStateStr("维修中");
                break;
            case 8:
                vh.setText(R.id.tv_repair_state, "维修完成");
                repairOrderInfo.setStateStr("维修完成");
                break;
            case 9:
                vh.setText(R.id.tv_repair_state, "已评价");
                repairOrderInfo.setStateStr("已评价");
                break;
        }

        vh.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(repairOrderInfo);
                }
            }
        });
    }
}
