package com.honyum.owner.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.net.EditPersonInfoRequest;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;

public class EditCellAddressActivity extends BaseActivity {

    private TextView tvAddress;

    private double lat;

    private double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cell_address);

        initView();
    }

    private void initView() {

        tvAddress = (TextView) findViewById(R.id.tv_cell_address);

        final EditText etAddress = (EditText) findViewById(R.id.et_cell_address);

        initTitleBar(R.id.title, "修改小区地址", R.mipmap.back, backClickListener,
                "提交", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        submitEdit(etAddress.getText().toString());
                    }
                });

        tvAddress.setText(getConfig().getAddress());

        findViewById(R.id.ll_cell_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditCellAddressActivity.this, AddressSelActivity.class);
                startActivityForResult(intent, 101);
            }
        });
    }

    private void submitEdit(final String address) {

        if (Utils.isEmpty(tvAddress.getText().toString())) {
            showToast("请选择小区地址");
            return;
        }

        if (lat == 0 || lng == 0) {
            showToast("请先选择具体位置");
            return;
        }

        String server = getConfig().getServer() + NetConstant.EDIT_PERSON_INFO;

        EditPersonInfoRequest request = new EditPersonInfoRequest();
        RequestHead head = new RequestHead();
        EditPersonInfoRequest.EditPersonInfoReqBody body
                = request.new EditPersonInfoReqBody();

        head.setAccessToken(getConfig().getToken());
        head.setUserId(getConfig().getUserId());

        body.setAddress(tvAddress.getText() + address);
        body.setLat(lat);
        body.setLat(lng);

        request.setHead(head);
        request.setBody(body);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                showToast("修改成功");
                getConfig().setAddress(tvAddress.getText() + address);
                getConfig().setLat(lat);
                getConfig().setLng(lng);
                onBackPressed();
            }
        };

        addTask(netTask);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            double[] latlng = data.getDoubleArrayExtra("latlng");
            String address = data.getStringExtra("address_name");

            tvAddress.setText(address);

            lat = latlng[0];
            lng = latlng[1];
        }
    }
}
