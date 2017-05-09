package com.honyum.owner.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.honyum.owner.R;
import com.honyum.owner.data.RepairTaskInfo;
import com.honyum.owner.utils.Utils;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/1/9 0009
 */

public class RepairTaskAdapter extends BaseQuickAdapter<RepairTaskInfo> {


    public RepairTaskAdapter(List<RepairTaskInfo> data) {
        super(R.layout.layout_repair_order_item, data);
    }

    @Override
    protected void convert(BaseViewHolder vh, RepairTaskInfo info) {
        vh.setText(R.id.tv_repair_time, info.getCode())
                .setText(R.id.tv_repair_fault, "维修人员姓名:" + info.getWorkerName()
                        + "\t\t电话:" + info.getWorkerTel());

        int state = Utils.getInt(info.getState());

        switch (state) {
            case 1:
                vh.setText(R.id.tv_repair_state, "待出发");
                info.setStateStr("待出发");
                break;
            case 2:
                vh.setText(R.id.tv_repair_state, "已出发");
                info.setStateStr("已出发");
                break;
            case 3:
                vh.setText(R.id.tv_repair_state, "工作中");
                info.setStateStr("工作中");
                break;
            case 5:
                vh.setText(R.id.tv_repair_state, "检修完成");
                info.setStateStr("检修完成");
                break;
            case 6:
                vh.setText(R.id.tv_repair_state, "维修完成");
                info.setStateStr("维修完成");
                break;
        }
    }
}
