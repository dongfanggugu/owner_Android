package com.honyum.owner.activity.bxxd;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;


public class ElevatorInsuranceActivity extends BaseActivity {


    private static final List<Integer> imgs = new ArrayList<Integer>() {
        {
            add(R.mipmap.dtbx_page1);
            add(R.mipmap.dtbx_page2);
            add(R.mipmap.dtbx_page3);
            add(R.mipmap.dtbx_page4);
            add(R.mipmap.dtbx_page5);
            add(R.mipmap.dtbx_page6);
            add(R.mipmap.dtbx_page7);
            add(R.mipmap.dtbx_page8);
        }
    };

    private RecyclerView recyclerView;

    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevator_insurance);

        initTitleBar(R.id.title, "保险投保单", R.mipmap.back, backClickListener, 0, null);

        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {

                if (velocityX > 0 && currentPosition < imgs.size()) {
                    currentPosition++;
                } else if (velocityX < 0 && currentPosition > 0) {
                    currentPosition--;
                }

                recyclerView.smoothScrollToPosition(currentPosition);

                return true;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == 0) {
                    recyclerView.smoothScrollToPosition(currentPosition);
                }
            }
        });
    }

    private class MyAdapter extends BaseQuickAdapter<Integer> {

        MyAdapter() {
            super(R.layout.layout_image_item, imgs);
        }

        @Override
        protected void convert(BaseViewHolder vh, Integer i) {
            PhotoView photoView = (PhotoView) vh.convertView.findViewById(R.id.photoView);
//            LImageView photoView = (LImageView) vh.convertView.findViewById(R.id.iv);
            Glide.with(ElevatorInsuranceActivity.this).load(i).into(photoView);
        }
    }
}
