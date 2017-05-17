package com.honyum.owner.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.honyum.owner.R;
import com.honyum.owner.activity.common.CommonWebActivity;
import com.honyum.owner.activity.dtcs.HelpWebViewActivity;
import com.honyum.owner.activity.jxxd.AddRepair2Activity;
import com.honyum.owner.activity.jxxd.AddRepairActivity;
import com.honyum.owner.activity.dtsc.ElevatorMallActivity;
import com.honyum.owner.activity.dtcs.HelpCenterActivity;
import com.honyum.owner.activity.bxxd.InsuranceActivity;
import com.honyum.owner.activity.wbxd.MaintenanceActivity;
import com.honyum.owner.activity.wbxd.NearMaintenanceActivity;
import com.honyum.owner.activity.zzfwxd.ValueAddedServiceActivity;
import com.honyum.owner.adapter.BannerAdapter;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.base.Constant;
import com.honyum.owner.data.BannerInfo;
import com.honyum.owner.net.BannerResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.utils.Utils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessFragment extends Fragment {


    private View mView;

    private BaseActivity mActivity;

    public BusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mActivity = (BaseActivity) getActivity();
        mView = inflater.inflate(R.layout.fragment_business, container, false);

        requestBanner();

        initView();

        return mView;
    }

    private void initView() {
        mView.findViewById(R.id.ll_maintenance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(mActivity, MaintenanceActivity.class));

                startActivity(new Intent(mActivity, NearMaintenanceActivity.class));
            }
        });

        mView.findViewById(R.id.ll_repair).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(mActivity.getConfig().getUserId())) {
                    startActivity(new Intent(mActivity, AddRepairActivity.class));
                } else {
                    startActivity(new Intent(mActivity, AddRepair2Activity.class));
                }
            }
        });

        mView.findViewById(R.id.ll_elevator_insurance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, InsuranceActivity.class));
            }
        });

        mView.findViewById(R.id.ll_elevator_nous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, HelpWebViewActivity.class));
            }
        });

        mView.findViewById(R.id.ll_elevator_mall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, ElevatorMallActivity.class));
            }
        });

        mView.findViewById(R.id.ll_value_added_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, ValueAddedServiceActivity.class));
            }
        });

        mView.findViewById(R.id.iv_expert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, CommonWebActivity.class);
                intent.putExtra("title", "专家团队");
                intent.putExtra("url", mActivity.getConfig().getIp() + "static/h5/expert.html");

                startActivity(intent);
            }
        });

        mView.findViewById(R.id.iv_honyum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, CommonWebActivity.class);
                intent.putExtra("title", "中建华宇");
                intent.putExtra("url", mActivity.getConfig().getIp() + "static/h5/honyum.html");

                startActivity(intent);
            }
        });
    }

    private void requestBanner() {
        String server = mActivity.getConfig().getServer() + NetConstant.GET_BANNER;



        String request = Constant.EMPTY_REQUEST;

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                BannerResponse response = BannerResponse.getResult(result);
                initBannerView(response.getBody());
            }
        };

        mActivity.addBackGroundTask(netTask);
    }

    private int prePos;

    private int curItemPos;

    private void initBannerView(final List<BannerInfo> pics) {
        View view = mView.findViewById(R.id.banner_view);

        final ViewPager vp = (ViewPager) view.findViewById(R.id.viewPager);
        final BannerAdapter adapter = new BannerAdapter(getActivity(), pics);
        vp.setAdapter(adapter);
        vp.setCurrentItem(adapter.getCount() / 2);
        curItemPos = adapter.getCount() / 2;

        final LinearLayout llIndicator = (LinearLayout) view.findViewById(R.id.ll_indicator);
        for (BannerInfo pic : pics) {
            ImageView iv = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
            iv.setLayoutParams(params);
            iv.setBackgroundResource(R.drawable.sel_page_indicator);
            llIndicator.addView(iv);
        }
        llIndicator.getChildAt(0).setEnabled(false);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                llIndicator.getChildAt(position % pics.size()).setEnabled(false);
                llIndicator.getChildAt(prePos).setEnabled(true);
                prePos = position % pics.size();
                curItemPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                vp.setCurrentItem(curItemPos++);
                handler.postDelayed(this, 5000);
            }
        }, 5000);
    }

}
