package com.honyum.owner.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.honyum.owner.R;
import com.honyum.owner.data.ElevatorInfo;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/3/2 0002
 */

public class ElevatorBrandRecyAdapter extends BaseQuickAdapter<ElevatorInfo> {

    public ElevatorBrandRecyAdapter(List<ElevatorInfo> data) {
        super(R.layout.layout_elevator_brand_item, data);
    }

    @Override
    protected void convert(BaseViewHolder vh, ElevatorInfo elevatorInfo) {
        vh.setText(R.id.tv_elevator_brand, elevatorInfo.getName());
    }
}
