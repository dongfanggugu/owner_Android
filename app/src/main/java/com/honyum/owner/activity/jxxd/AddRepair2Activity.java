package com.honyum.owner.activity.jxxd;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.honyum.owner.R;
import com.honyum.owner.adapter.ElevatorFaultAdapter;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.base.Constant;
import com.honyum.owner.data.AddRepairReqBody;
import com.honyum.owner.data.ElevatorFaultInfo;
import com.honyum.owner.net.AddRepairRequest;
import com.honyum.owner.net.ElevatorFaultResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;

import java.util.Calendar;

public class AddRepair2Activity extends BaseActivity {

    private Spinner spElevatorFault;

    private String repairTypeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repair2);

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
                        new ElevatorFaultAdapter(AddRepair2Activity.this, response.getBody());
                spElevatorFault.setAdapter(adapter);
                repairTypeId = response.getBody().get(0).getId();
            }
        };

        addBackGroundTask(netTask);
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_lift_brand)).setText(getConfig().getBrand());
        ((TextView) findViewById(R.id.tv_lift_model)).setText(getConfig().getModel());
        ((TextView) findViewById(R.id.tv_owner_name)).setText(getConfig().getName());
        ((TextView) findViewById(R.id.tv_owner_tel)).setText(getConfig().getTel());
        ((TextView) findViewById(R.id.tv_sel_address)).setText(getConfig().getAddress());

        spElevatorFault = (Spinner) findViewById(R.id.sp_lift_fault_type);
        final EditText etFaultDescribe = (EditText) findViewById(R.id.et_lift_fault_describe);
        final TextView tvSelDateTime = (TextView) findViewById(R.id.tv_sel_datetime);

        spElevatorFault.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ElevatorFaultInfo info = (ElevatorFaultInfo) parent.getAdapter().getItem(position);
                repairTypeId = info.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        tvSelDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDateTime(tvSelDateTime);
            }
        });

        initTitleBar(R.id.title, "急修服务", R.mipmap.back, backClickListener,
                0, null);

        findViewById(R.id.tv_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRepair(etFaultDescribe.getText().toString(),
                        tvSelDateTime.getText().toString());
            }
        });
    }

    private void addRepair(String faultDescribe, String dateTime) {

        if (Utils.isEmpty(dateTime)) {
            showToast("请选择上门时间!");
            return;
        }

        if (Utils.isEmpty(repairTypeId)) {
            return;
        }

        String server = getConfig().getServer() + NetConstant.ADD_REPAIR_ORDER;
        AddRepairRequest request = new AddRepairRequest();
        RequestHead head = new RequestHead();
        AddRepairReqBody body = new AddRepairReqBody();

        head.setAccessToken(getConfig().getToken());
        head.setUserId(getConfig().getUserId());

        body.setRepairTypeId(repairTypeId);
        body.setPhenomenon(faultDescribe);
        body.setRepairTime(dateTime);

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
        builder.setMessage("添加快修订单成功!");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();
    }

    private void chooseDateTime(final TextView tvDateTime) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddRepair2Activity.this,
                        new TimePickerDialog.OnTimeSetListener() {
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
}
