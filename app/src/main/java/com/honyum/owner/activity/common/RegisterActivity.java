package com.honyum.owner.activity.common;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.honyum.owner.R;
import com.honyum.owner.adapter.ElevatorBrandAdapter;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.base.Constant;
import com.honyum.owner.data.ElevatorInfo;
import com.honyum.owner.net.ElevatorBrandResponse;
import com.honyum.owner.net.RegisterRequest;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;

import java.util.List;

public class RegisterActivity extends BaseActivity {

    private double lat;

    private double lng;

    private TextView tvAddress;

    private TextView tvBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initTitleBar(R.id.title, "注册", R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        }, 0, null);

        initView();

    }

    private void requestEtBrand() {
        String server = getConfig().getServer() + NetConstant.GET_ELEVATOR_BRAND;
        final String request = Constant.EMPTY_REQUEST;

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
       // final EditText etUserName = (EditText) findViewById(R.id.et_user_name);
        final EditText etName = (EditText) findViewById(R.id.et_name);
        final EditText etTel = (EditText) findViewById(R.id.et_tel);
        final EditText etAddress = (EditText) findViewById(R.id.et_cell_address);
        final EditText etPwd1 = (EditText) findViewById(R.id.et_pwd1);
        final EditText etPwd2 = (EditText) findViewById(R.id.et_pwd2);
        tvAddress = (TextView) findViewById(R.id.tv_cell_address);

        tvBrand = (TextView) findViewById(R.id.tv_brand);

        tvBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestEtBrand();
            }
        });

        findViewById(R.id.ll_cell_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRunTimePermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent(RegisterActivity.this, AddressSelActivity.class);
                        startActivityForResult(intent, 101);
                    }

                    @Override
                    public void onDenied(List<String> permission) {
                    }
                });
            }
        });

        findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerOwner(etName.getText().toString(),
                        etTel.getText().toString(), etAddress.getText().toString(),
                        etPwd1.getText().toString(), etPwd2.getText().toString());
            }

        });
    }

    private void registerOwner(String name, final String tel, String address, String pwd1, final String pwd2) {

        if (Utils.isEmpty(name) || Utils.isEmpty(tel)
                || Utils.isEmpty(tvAddress.getText().toString())
                || Utils.isEmpty(pwd1) || Utils.isEmpty(pwd2)) {
            showToast("信息填写不完整!");
            return;
        }

        if (tvBrand.getText().equals("点击选择电梯品牌")
                || Utils.isEmpty(tvBrand.getText().toString())) {
            showToast("请选择您的电梯品牌");
            return;
        }
        if (!Utils.isMobileNumber(tel)) {
            showToast("手机号码格式不正确!");
            return;
        }

        if (!pwd1.equals(pwd2)) {
            showToast("两次密码填写不一致!");
            return;
        }

        if (lat == 0 || lng == 0) {
            showToast("未选择具体位置!");
            return;
        }

        String server = getConfig().getServer() + NetConstant.OWNER_REGISTER;

        RegisterRequest request = new RegisterRequest();
        RequestHead head = new RequestHead();
        RegisterRequest.RegisterReqBody body = request.new RegisterReqBody();

        body.setName(name);
        body.setTel(tel);
        body.setBrand(tvBrand.getText().toString());
        body.setAddress(tvAddress.getText() + address);
        body.setPassword(Utils.encryptMD5(pwd2));
        body.setLat(lat);
        body.setLng(lng);

        request.setBody(body);
        request.setHead(head);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                showSuccessDialog(tel, pwd2);
            }
        };

        addTask(netTask);
    }


    private void showSuccessDialog(String tel, String pwd) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("注册成功");
        builder.setMessage("使用用户名或手机号即可登录");

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                onBackPressed();
            }
        });

        builder.create().show();
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

    private void showBrandListDialog(List<ElevatorInfo> infoList) {
        View view = View.inflate(this, R.layout.layout_dialog_list, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(view);

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

        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(view);

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

    private class ListViewAdapter extends BaseAdapter{

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
