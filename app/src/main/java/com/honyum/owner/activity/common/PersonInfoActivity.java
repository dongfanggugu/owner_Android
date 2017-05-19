package com.honyum.owner.activity.common;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.honyum.owner.R;
import com.honyum.owner.activity.wbxd.AddMtOrderActivity;
import com.honyum.owner.adapter.ElevatorBrandRecyAdapter;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.base.Constant;
import com.honyum.owner.data.ElevatorInfo;
import com.honyum.owner.net.EditPersonInfoRequest;
import com.honyum.owner.net.ElevatorBrandResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;
import com.honyum.owner.view.CircleImageView;

import java.io.File;
import java.util.List;

public class PersonInfoActivity extends BaseActivity {

    private static final int CAMERA_REQ_CODE = 101;

    private static final int PICKER_REQ_CODE = 102;

    private static final int CROP_REQ_CODE = 103;

    private Uri cameraUri;

    private TextView mTvBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        initTitleBar(R.id.title, "基本资料", R.mipmap.back, backClickListener, 0, null);

        CircleImageView ivAvatar = (CircleImageView) findViewById(R.id.iv_avatar);
        File avatarFile = new File(Utils.getUserAvatarPath(), getConfig().getUserId() + ".jpg");
        if (avatarFile.exists()) {
            ivAvatar.setImageBitmap(BitmapFactory.decodeFile(avatarFile.getAbsolutePath()));
        } else {
            ivAvatar.setImageResource(R.mipmap.icon_avator_default2);
        }


//        findViewById(R.id.ll_setting_avatar).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showPopupWindow();
//            }
//        });

//        TextView tvUserName = (TextView) findViewById(R.id.tv_user_name);
//        tvUserName.setText(getConfig().getUserName());

        TextView tvName = (TextView) findViewById(R.id.tv_owner_name);
        if (Utils.isEmpty(getConfig().getName())) {
            tvName.setText("待完善");
        } else {
            tvName.setText(getConfig().getName());
        }

        TextView tvTel = (TextView) findViewById(R.id.tv_owner_tel);
        tvTel.setText(getConfig().getTel());

        TextView tvLinkName = (TextView) findViewById(R.id.tv_link_name);
        tvLinkName.setText(getConfig().getLinkName());

        TextView tvLinkTel = (TextView) findViewById(R.id.tv_link_tel);
        tvLinkTel.setText(getConfig().getLinkTel());

        mTvBrand = (TextView) findViewById(R.id.tv_et_brand);
        mTvBrand.setText(getConfig().getBrand());

        TextView tvEtModel = (TextView) findViewById(R.id.tv_et_model);
        if (Utils.isEmpty(getConfig().getModel())) {
            tvEtModel.setText("——");
        } else {
            tvEtModel.setText(getConfig().getModel());
        }

//        TextView tvCellName = (TextView) findViewById(R.id.tv_cell_name);
//        tvCellName.setText(getConfig().getCellName());

        TextView tvAddress = (TextView) findViewById(R.id.tv_address);
        tvAddress.setText(getConfig().getAddress());

        findViewById(R.id.ll_owner_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonInfoActivity.this, EditPersonInfoActivity.class);
                intent.putExtra("edit_type", 0);
                startActivity(intent);
            }
        });

        findViewById(R.id.ll_link_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonInfoActivity.this, LinkModifyActivity.class));
            }
        });

        findViewById(R.id.ll_link_tel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonInfoActivity.this, LinkModifyActivity.class));
            }
        });

        findViewById(R.id.ll_elevator_brand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestBrand();
            }
        });

        findViewById(R.id.ll_elevator_model).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonInfoActivity.this, EditPersonInfoActivity.class);
                intent.putExtra("edit_type", 1);
                startActivity(intent);
            }
        });

        findViewById(R.id.ll_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonInfoActivity.this, ShowAddressActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.fl_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }


    private void submitEdit(final String brand) {

        String server = getConfig().getServer() + NetConstant.EDIT_PERSON_INFO;

        EditPersonInfoRequest request = new EditPersonInfoRequest();
        RequestHead head = new RequestHead();
        EditPersonInfoRequest.EditPersonInfoReqBody body
                = request.new EditPersonInfoReqBody();

        head.setAccessToken(getConfig().getToken());
        head.setUserId(getConfig().getUserId());

        body.setBrand(brand);

        request.setHead(head);
        request.setBody(body);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                mTvBrand.setText(brand);
                getConfig().setBrand(brand);
            }
        };

        addTask(netTask);
    }

    /**
     * 选择拍照或相册选取
     */
    private void showPopupWindow() {
        View view = View.inflate(this, R.layout.layout_setting_avatar_popupwindow, null);

        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.popupWindowAnim);
        popupWindow.showAtLocation(findViewById(R.id.activity_person_setting), Gravity.BOTTOM, 0, 0);

        setBackgroundAlpha(0.5f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });

        view.findViewById(R.id.tv_photograph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRunTimePermission(new String[]{Manifest.permission.CAMERA}, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        popupWindow.dismiss();
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, CAMERA_REQ_CODE);
                    }

                    @Override
                    public void onDenied(List<String> permission) {
                    }
                });
            }
        });

        view.findViewById(R.id.tv_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRunTimePermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        popupWindow.dismiss();
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent, PICKER_REQ_CODE);
                    }

                    @Override
                    public void onDenied(List<String> permission) {
                    }
                });
            }
        });
    }


    /**
     * 对图片进行裁剪
     *
     * @param uri
     */
    private void cropImage(Uri uri) {
        if (null == uri) {
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", true);

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 160);
        intent.putExtra("outputY", 160);

        intent.putExtra("return-data", true);

        startActivityForResult(intent, CROP_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQ_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            cameraUri = uri;

            cropImage(uri);
        }

        if (requestCode == PICKER_REQ_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            cropImage(uri);
        }

        if (requestCode == CROP_REQ_CODE && resultCode == RESULT_OK && data != null) {

            if (cameraUri != null) {
                String path = Utils.getAbsolutePathFromUri(PersonInfoActivity.this, cameraUri);
                //删除图片
                Utils.deleteFiles(path);
                String where = MediaStore.Images.Media.DATA + "='" + path + "'";
                getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, where, null);
            }


            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            String userAvatarPath = Utils.getUserAvatarPath();

            if (Utils.isEmpty(userAvatarPath)) {
                showToast("文件保存失败,请稍后重试");
                return;
            }

            File avatarFile = new File(userAvatarPath, getConfig().getUserId() + ".jpg");

            if (Utils.saveImgFile(avatarFile, bitmap)) {
                showToast("头像保存成功");
                CircleImageView ivAvatar = (CircleImageView) findViewById(R.id.iv_avatar);
                ivAvatar.setImageBitmap(bitmap);
            } else {
                showToast("头像保存失败,请重试");
            }
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        this.getWindow().setAttributes(lp);
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
                    submitEdit(info.getName());
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

                submitEdit(brand);
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
