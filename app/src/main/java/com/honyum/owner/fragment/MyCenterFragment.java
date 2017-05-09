package com.honyum.owner.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.activity.LoginActivity;
import com.honyum.owner.activity.wbdd.MtOrderActivity;
import com.honyum.owner.activity.common.PersonInfoActivity;
import com.honyum.owner.activity.jxdd.RepairOrderActivity;
import com.honyum.owner.activity.common.SettingsActivity;
import com.honyum.owner.activity.zzfwdd.ZzfwActivity;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCenterFragment extends Fragment {


    private View mView;

    private BaseActivity mActivity;

    public MyCenterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_my_center, container, false);
        mActivity = (BaseActivity) getActivity();

        initView();

        return mView;
    }

    private void initView() {
        final String userId = mActivity.getConfig().getUserId();

        if (!Utils.isEmpty(userId)) {
            TextView tvUserName = (TextView) mView.findViewById(R.id.tv_user_name);
            tvUserName.setText(mActivity.getConfig().getName());
        }

        mView.findViewById(R.id.ll_user_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(userId)) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 101);
                } else {
                    Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                    startActivity(intent);
                }
            }
        });

        mView.findViewById(R.id.ll_my_mt_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(userId)) {
                    showTipDialog();
                } else {
                    Intent intent = new Intent(getActivity(), MtOrderActivity.class);
                    startActivity(intent);
                }
            }
        });

        mView.findViewById(R.id.ll_my_rp_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(userId)) {
                    showTipDialog();
                } else {
                    Intent intent = new Intent(getActivity(), RepairOrderActivity.class);
                    startActivity(intent);
                }
            }
        });

        mView.findViewById(R.id.ll_zzfw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(userId)) {
                    showTipDialog();
                } else {
                    Intent intent = new Intent(getActivity(), ZzfwActivity.class);
                    startActivity(intent);
                }
            }
        });

        mView.findViewById(R.id.ll_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showTipDialog() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setTitle("提示");
        dialog.setMessage("当前未登录");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "登录/注册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 101);
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initView();
    }
}
