package com.honyum.owner.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.data.ElevatorFaultInfo;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/2/28 0028
 */

public class ElevatorFaultAdapter extends MyBaseAdapter<ElevatorFaultInfo> {

    public ElevatorFaultAdapter(Context context, List<ElevatorFaultInfo> dataSource) {
        super(context, dataSource);
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {

        ElevatorFaultInfo item = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_elevator_brand_item, parent, false);
        }

        TextView tvBrand = (TextView) convertView.findViewById(R.id.tv_elevator_brand);
        tvBrand.setText(item.getName());

        return convertView;
    }
}
