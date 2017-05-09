package com.honyum.owner.activity.wbxd;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.activity.common.AddressSelActivity;
import com.honyum.owner.activity.common.PaymentActivity;
import com.honyum.owner.adapter.ElevatorBrandAdapter;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.base.Constant;
import com.honyum.owner.data.AddMtOrderReqBody;
import com.honyum.owner.data.ElevatorInfo;
import com.honyum.owner.data.MtTypeInfo;
import com.honyum.owner.net.AddMtOrderRequest;
import com.honyum.owner.net.ElevatorBrandResponse;
import com.honyum.owner.net.PayUrlResponse;
import com.honyum.owner.net.SmsCodeResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;

import java.util.List;

public class AddMtOrderActivity extends BaseActivity {

    private int time = 60;

    private AddMtOrderReqBody body;

    private Spinner spElevatorBrand;

    private String smsCode;

    private TextView tvSelAdd;

    private String brand;

    private double lat, lng;

    private int frequency = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mt_order);

        body = new AddMtOrderReqBody();

        initView();

        requestElevatorBrand();
    }

    private void requestElevatorBrand() {
        String server = getConfig().getServer() + NetConstant.GET_ELEVATOR_BRAND;
        String request = Constant.EMPTY_REQUEST;

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                ElevatorBrandResponse response = ElevatorBrandResponse.getResult(result);
                ElevatorBrandAdapter adapter =
                        new ElevatorBrandAdapter(AddMtOrderActivity.this, response.getBody());
                spElevatorBrand.setAdapter(adapter);
                body.setBrand(response.getBody().get(0).getName());
            }
        };

        addBackGroundTask(netTask);
    }

    private void initView() {
        final MtTypeInfo mtInfo = (MtTypeInfo) getIntent().getSerializableExtra("data");

        ((TextView) findViewById(R.id.tv_mt_type)).setText(mtInfo.getName() + " ￥" + mtInfo.getPrice());
        ((TextView) findViewById(R.id.tv_mt_content)).setText(mtInfo.getContent());

        final TextView tvWbjg = (TextView) findViewById(R.id.wbjg);
        final TextView tvCount = (TextView) findViewById(R.id.tv_count);

        findViewById(R.id.iv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCount.setText("" + (frequency += 1) + "");
                tvWbjg.setText("￥" + (frequency * mtInfo.getPrice()) + "");
            }
        });

        findViewById(R.id.iv_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (frequency == 1)
                    return;
                tvCount.setText("" + (frequency -= 1) + "");
                tvWbjg.setText("￥" + (frequency * mtInfo.getPrice()) + "");
            }
        });


        spElevatorBrand = (Spinner) findViewById(R.id.sp_lift_brand);
        spElevatorBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ElevatorInfo info = (ElevatorInfo) parent.getAdapter().getItem(position);
                body.setBrand(info.getName());
                brand = info.getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final EditText etElevatorModel = (EditText) findViewById(R.id.et_lift_model);
        final EditText etOwnerName = (EditText) findViewById(R.id.et_owner_name);
        final EditText etDetailAdd = (EditText) findViewById(R.id.et_detail_address);

        tvSelAdd = (TextView) findViewById(R.id.tv_sel_address);
        findViewById(R.id.ll_sel_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRunTimePermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent(AddMtOrderActivity.this, AddressSelActivity.class);
                        startActivityForResult(intent, 101);
                    }

                    @Override
                    public void onDenied(List<String> permission) {
                    }
                });
            }
        });


        final EditText etOwnerTel = (EditText) findViewById(R.id.et_owner_tel);
        final Handler handler = new Handler();
        final Button btnVCode = (Button) findViewById(R.id.btn_get_verify_code);
        btnVCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.isEmpty(mtInfo.getId()) || Utils.isEmpty(etOwnerName.getText().toString())
                        || lat == 0.0 || lng == 0.0 || Utils.isEmpty(body.getBrand())) {
                    showToast("请完善用户信息!");
                    return;
                }

                if (Utils.isEmpty(etOwnerTel.getText().toString())) {
                    showToast("手机号不能为空!");
                    return;
                }

                getSMSCode(etOwnerTel.getText().toString());

                btnVCode.setEnabled(false);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        if (time >= 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    btnVCode.setText("" + time + "m后重新获取");
                                }
                            });
                            handler.postDelayed(this, 1000);
                        } else {
                            time = 60;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    btnVCode.setText("重新获取验证码");
                                    btnVCode.setEnabled(true);
                                }
                            });
                        }
                    }
                });
            }
        });

        final EditText etSmsCode = (EditText) findViewById(R.id.et_verify_code);

        initTitleBar(R.id.title, "维保订单", R.mipmap.back, backClickListener,
                0, null);

        findViewById(R.id.tv_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMtOrder(mtInfo.getId(), etElevatorModel.getText().toString(),
                        etOwnerName.getText().toString(),
                        etDetailAdd.getText().toString(),
                        etOwnerTel.getText().toString(),
                        etSmsCode.getText().toString());
            }
        });
    }

    private void addMtOrder(String mainttypeId, final String model, final String name,
                            final String detailAdd, final String tel, String smsCode) {
        if (Utils.isEmpty(mainttypeId) || Utils.isEmpty(name)
                || Utils.isEmpty(tel) || Utils.isEmpty(body.getBrand())
                || lat == 0.0 || lng == 0.0) {
            showToast("请完善用户信息!");
            return;
        }

        if (!this.smsCode.equals(smsCode)) {
            showToast("验证码输入错误,请重新输入!");
            return;
        }

        String server = getConfig().getServer() + NetConstant.ADD_MT_ORDER;
        AddMtOrderRequest request = new AddMtOrderRequest();
        RequestHead head = new RequestHead();

        body.setModel(model);
        body.setAddress(tvSelAdd.getText() + detailAdd);
        body.setMainttypeId(mainttypeId);
        body.setName(name);
        body.setTel(tel);
        body.setLat(lat);
        body.setLng(lng);
        body.setFrequency(frequency);

        request.setHead(head);
        request.setBody(body);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {

                getConfig().setName(name);
                getConfig().setTel(tel);
                getConfig().setBrand(brand);
                getConfig().setModel(model);
                getConfig().setAddress(tvSelAdd.getText() + detailAdd);
                getConfig().setLat(lat);
                getConfig().setLng(lng);

                PayUrlResponse.PayUrlResBody resBody = PayUrlResponse.getResult(result);
                Intent intent = new Intent(AddMtOrderActivity.this, PaymentActivity.class);
                intent.putExtra("url", resBody.getUrl());
                startActivity(intent);
                finish();
            }
        };

        addTask(netTask);
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("提示");
        builder.setMessage("添加维保订单成功!\n\n请使用下单手机号登录,密码默认为123456," +
                "为了安全起见请自行修改密码");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();
    }

    private void getSMSCode(String tel) {
        String server = getConfig().getServer() + NetConstant.GET_SMS_CODE;
        String request = "{\"head\":{\"osType\":\"2\"},\"body\":{\"tel\":" + tel + "}}";

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                SmsCodeResponse response = SmsCodeResponse.getResult(result);
                smsCode = response.getBody().getCode();
            }
        };

        addBackGroundTask(netTask);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            double[] latlng = data.getDoubleArrayExtra("latlng");
            String address = data.getStringExtra("address_name");

            tvSelAdd.setText(address);

            lat = latlng[0];
            lng = latlng[1];
        }
    }
}
