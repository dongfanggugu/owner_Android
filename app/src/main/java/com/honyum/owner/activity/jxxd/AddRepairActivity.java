package com.honyum.owner.activity.jxxd;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.honyum.owner.R;
import com.honyum.owner.activity.common.AddressSelActivity;
import com.honyum.owner.activity.wbxd.AddMtOrderActivity;
import com.honyum.owner.adapter.ElevatorBrandAdapter;
import com.honyum.owner.adapter.ElevatorFaultAdapter;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.base.Constant;
import com.honyum.owner.data.AddRepairReqBody;
import com.honyum.owner.data.ElevatorFaultInfo;
import com.honyum.owner.data.ElevatorInfo;
import com.honyum.owner.net.AddRepairRequest;
import com.honyum.owner.net.ElevatorBrandResponse;
import com.honyum.owner.net.ElevatorFaultResponse;
import com.honyum.owner.net.SmsCodeResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;

import java.util.Calendar;
import java.util.List;

public class AddRepairActivity extends BaseActivity {

    private int time = 60;

    private Spinner spElevatorFault;

    private AddRepairReqBody body;

    private TextView tvSelAdd;

    private double lat, lng;

    private String smsCode = "-1";

    private TextView tvBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repair);

        body = new AddRepairReqBody();

        initView();

        requestElevatorFault();
    }

    private void requestElevatorFault() {
        String server = getConfig().getServer() + NetConstant.GET_REPAIR_TYPE_LIST;
        String request = Constant.EMPTY_REQUEST;

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                ElevatorFaultResponse response = ElevatorFaultResponse.getResult(result);
                ElevatorFaultAdapter adapter =
                        new ElevatorFaultAdapter(AddRepairActivity.this, response.getBody());
                spElevatorFault.setAdapter(adapter);
                body.setRepairTypeId(response.getBody().get(0).getId());
            }
        };

        addBackGroundTask(netTask);
    }

    private void requestBrand() {
        String server = getConfig().getServer() + NetConstant.GET_ELEVATOR_BRAND;
        String request = Constant.EMPTY_REQUEST;

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                ElevatorBrandResponse response = ElevatorBrandResponse.getResult(result);
                showBrandListDialog(response.getBody());
            }
        };

        addBackGroundTask(netTask);
    }

    private void initView() {
        final EditText etElevatorModel = (EditText) findViewById(R.id.et_lift_model);
        spElevatorFault = (Spinner) findViewById(R.id.sp_lift_fault_type);
        final EditText etFaultDescribe = (EditText) findViewById(R.id.et_lift_fault_describe);
        final TextView tvSelDateTime = (TextView) findViewById(R.id.tv_sel_datetime);
        tvSelDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDateTime(tvSelDateTime);
            }
        });
        final EditText etOwnerName = (EditText) findViewById(R.id.et_owner_name);
        final EditText etDetailAdd = (EditText) findViewById(R.id.et_detail_address);

        tvBrand = (TextView) findViewById(R.id.tv_brand);

        tvBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestBrand();
            }
        });

        spElevatorFault.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ElevatorFaultInfo info = (ElevatorFaultInfo) parent.getAdapter().getItem(position);
                body.setRepairTypeId(info.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        tvSelAdd = (TextView) findViewById(R.id.tv_sel_address);
        findViewById(R.id.ll_sel_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRunTimePermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent(AddRepairActivity.this, AddressSelActivity.class);
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

                if (Utils.isEmpty(tvSelDateTime.getText().toString())
                        || Utils.isEmpty(etOwnerName.getText().toString())
                        || lat == 0.0 || lng == 0.0) {
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

        initTitleBar(R.id.title, "急修服务", R.mipmap.back, backClickListener,
                0, null);

        findViewById(R.id.tv_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRepair(etElevatorModel.getText().toString(), etFaultDescribe.getText().toString(),
                        tvSelDateTime.getText().toString(), etOwnerName.getText().toString(),
                        etOwnerTel.getText().toString(), etDetailAdd.getText().toString(),
                        etSmsCode.getText().toString());
            }
        });
    }

    private void addRepair(String model, String faultDescribe, String dateTime,
                           String name, String tel, String detailAdd, String smsCode) {

        if (Utils.isEmpty(dateTime) || Utils.isEmpty(name) || Utils.isEmpty(tel)
                || lat == 0.0 || lng == 0.0) {
            showToast("请完善用户信息");
            return;
        }

        EditText etLinkName = (EditText) findViewById(R.id.et_link_name);
        EditText etLinkTel = (EditText) findViewById(R.id.et_link_tel);

        if (Utils.isEmpty(etLinkName.getText().toString())
                || Utils.isEmpty(etLinkTel.getText().toString())) {
            showToast("请先写联系人信息!");
            return;
        }

        if (!this.smsCode.equals(smsCode)) {
            showToast("验证码输入错误,请重新输入!");
            return;
        }

        String server = getConfig().getServer() + NetConstant.ADD_REPAIR_ORDER;
        AddRepairRequest request = new AddRepairRequest();
        RequestHead head = new RequestHead();
        body.setPhenomenon(faultDescribe);
        body.setName(name);
        body.setTel(tel);
        body.setAddress(tvSelAdd.getText() + detailAdd);
        body.setModel(model);
        body.setRepairTime(dateTime);
        body.setLat(lat);
        body.setLat(lng);

        body.setContacts(etLinkName.getText().toString());
        body.setContactsTel(etLinkTel.getText().toString());

        request.setHead(head);
        request.setBody(body);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                showSuccessDialog();
            }
        };

        addTask(netTask);
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("提示");
        builder.setMessage("添加快修订单成功!\n\n请使用下单手机号登录,密码默认为123456," +
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

    /**
     * 选择时间
     */
    private void chooseDateTime(final TextView tvDateTime) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddRepairActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String mon = (month + 1) + "";
                        String day = dayOfMonth + "";

                        if (month < 9) {
                            mon = "0" + mon;
                        }

                        if (dayOfMonth < 10) {
                            day = "0" + dayOfMonth;
                        }

                        String hour = hourOfDay + "";
                        String min = minute + "";

                        if (hourOfDay < 10) {
                            hour = "0" + hour;
                        }

                        if (minute < 10) {
                            min = "0" + min;
                        }

                        tvDateTime.setText(year + "-" + mon + "-" + day + " " + hour + ":" + min);
                    }
                }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        DatePicker dp = datePickerDialog.getDatePicker();
        dp.setMinDate(calendar.getTimeInMillis());

        datePickerDialog.show();
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

    private void showBrandListDialog(List<ElevatorInfo> infoList) {
        View view = View.inflate(this, R.layout.layout_dialog_list, null);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this).setView(view);

        Dialog dialog = builder.create();

        initListDialogView(dialog, view, infoList);

        dialog.show();
    }

    private void initListDialogView(final Dialog dialog, View view, List<ElevatorInfo> infoList) {

        ListView listView = (ListView) view.findViewById(R.id.listView);
        ListViewAdapter adapter = new ListViewAdapter(this, infoList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                ElevatorInfo info = (ElevatorInfo) view.getTag();

                if (info.getName().equals("其他")) {
                    showEditDialog();

                } else {
                    tvBrand.setText(info.getName());

                }

            }
        });

        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showEditDialog() {

        View view = View.inflate(this, R.layout.layout_edit_dialog, null);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this).setView(view);

        Dialog dialog = builder.create();

        initDialogView(dialog, view);

        dialog.show();

    }

    private void initDialogView(final Dialog dialog, final View view) {

        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etBrand = (EditText) view.findViewById(R.id.et_brand);
                String brand = etBrand.getText().toString();

                if (Utils.isEmpty(brand)) {
                    showToast("请填写您的电梯品牌");
                    return;
                }

                tvBrand.setText(brand);
                dialog.dismiss();
            }
        });
    }

    private class ListViewAdapter extends BaseAdapter {

        private List<ElevatorInfo> infos;

        private Context context;

        public ListViewAdapter(Context context, List<ElevatorInfo> list) {
            this.context = context;
            this.infos = list;
        }

        @Override
        public int getCount() {
            return this.infos.size();
        }

        @Override
        public Object getItem(int position) {
            return this.infos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (null == convertView) {
                convertView =  View.inflate(this.context, R.layout.layout_textview_item, null);
            }

            convertView.setTag(this.infos.get(position));
            TextView tvContent = (TextView) convertView.findViewById(R.id.tv_content);

            tvContent.setText(this.infos.get(position).getName());

            return convertView;
        }
    }
}
