package com.honyum.owner.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.honyum.owner.R;
import com.honyum.owner.data.MtTaskInfo;
import com.honyum.owner.utils.Utils;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/3/2 0002
 */

public class MtTaskAdapter extends BaseQuickAdapter<MtTaskInfo> {

    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(MtTaskAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onItemClick(MtTaskInfo info);
    }

    public MtTaskAdapter(List<MtTaskInfo> data) {
        super(R.layout.layout_mt_task_item, data);
    }

    @Override
    protected void convert(BaseViewHolder vh, final MtTaskInfo mtTaskInfo) {

        vh.setText(R.id.rwdh, mtTaskInfo.getTaskCode())
                .setText(R.id.lxfs, mtTaskInfo.getMaintUserInfo().getName() + " " + mtTaskInfo.getMaintUserInfo().getTel());

        int state = Utils.getInt(mtTaskInfo.getState());
        switch (state) {
            case 0:
                vh.setText(R.id.rwzt, "待确认");
                break;
            case 1:
                vh.setText(R.id.rwzt, "已确认");
                break;
            case 2:
                vh.setText(R.id.rwzt, "已完成");
                break;
            case 3:
                vh.setText(R.id.rwzt, "已评价");
                break;
        }

        vh.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(mtTaskInfo);
                }
            }
        });
    }
}
