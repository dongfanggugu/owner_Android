package com.honyum.owner.activity.wbxd;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.utils.DistanceUtil;
import com.honyum.owner.R;
import com.honyum.owner.activity.wbdd.MtDetailActivity;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.base.Constant;
import com.honyum.owner.common.service.LocationService;
import com.honyum.owner.data.MaintenanceProjectInfo;
import com.honyum.owner.data.MtTypeInfo;
import com.honyum.owner.data.PropertyAddressInfo;
import com.honyum.owner.net.AllCommunitysResponse;
import com.honyum.owner.net.AllMaintenanceProjectRequest;
import com.honyum.owner.net.MaintenanceTypeResponse;
import com.honyum.owner.net.base.NetConstant;
import com.honyum.owner.net.base.NetTask;
import com.honyum.owner.net.base.RequestHead;
import com.honyum.owner.utils.Utils;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearMaintenanceActivity extends BaseActivity {

    private MapView mMapView;

    private BaiduMap mBaiduMap;

    private Map<Marker, MaintenanceProjectInfo> markerMap;

    private Marker PerAddMarker;

    private List<PropertyAddressInfo> propertyAddressInfos;

    private LatLng stationPoint;

    private View floatView;

    private TextView tvAddress;

    private View view1;

    private View view2;

    private View view3;

    private TextView tvLevel1Top;

    private TextView tvLevel1Mid;

    private TextView tvLevel1Bottom;

    private TextView tvLevel2Top;

    private TextView tvLevel2Mid;

    private TextView tvLevel2Bottom;

    private TextView tvLevel3Top;

    private TextView tvLevel3Mid;

    private TextView tvLevel3Bottom;

    private TextView tvDetail;

    private TextView btnDetail;

    private TextView btnOrder;

    private int curIndex;

    private List<MtTypeInfo> mTypeInfos;

    private MyLocationReceiver mLocationReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(this.getApplicationContext());
        setContentView(R.layout.activity_near_maintenance);

        markerMap = new HashMap<Marker, MaintenanceProjectInfo>();

        initTitleBar();

        initView();

        requestMtProject();

        requestMt();
    }



    private void markAddress(double lat, double lng) {

        stationPoint = new LatLng(lat, lng);

        MapStatusUpdate update = MapStatusUpdateFactory.newLatLngZoom(stationPoint, 15);
        mBaiduMap.animateMapStatus(update);

        MarkerOptions options = (MarkerOptions) initOptions(R.drawable.marker_alarm_old);
        options.position(stationPoint);

        if (PerAddMarker == null) {
            PerAddMarker = (Marker) mBaiduMap.addOverlay(options);
        }

        PerAddMarker.setPosition(stationPoint);
    }


    private void initTitleBar() {
        initTitleBar(R.id.title, "电梯管家",
                R.mipmap.back, backClickListener,
                null, null);
    }


    private void initMapView() {
        mMapView = (MapView) findViewById(R.id.mapView);
        mBaiduMap = mMapView.getMap();

        //隐藏百度Logo
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mBaiduMap.hideInfoWindow();
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });

    }

    /**
     * 请求所有的维保项目
     */
    private void requestMtProject() {

        String server = getConfig().getServer() + NetConstant.GET_ALL_COMMUNITYS_BY_PROPERTY;

        AllMaintenanceProjectRequest request = new AllMaintenanceProjectRequest();
        RequestHead head = new RequestHead();
        AllMaintenanceProjectRequest.AllMaintenanceProjectReqBody body
                = request.new AllMaintenanceProjectReqBody();

        head.setAccessToken(getConfig().getToken());
        head.setUserId(getConfig().getUserId());

        request.setHead(head);
        request.setBody(body);

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                AllCommunitysResponse response = AllCommunitysResponse.getAllCommunity(result);
                List<MaintenanceProjectInfo> body = response.getBody();

                for (MaintenanceProjectInfo info : body) {
                    markMtPro(info);
                }
            }
        };

        addBackGroundTask(netTask);
    }

    private void markMtPro(MaintenanceProjectInfo info) {

        if (info.getLng() == 0 || info.getLat() == 0) {
            return;
        }

        LatLng point = new LatLng(info.getLat(), info.getLng());

        MarkerOptions options = (MarkerOptions) initOptions(R.drawable.marker_project);
        options.position(point);

        Marker marker = (Marker) mBaiduMap.addOverlay(options);
        markerMap.put(marker, info);
    }


    private OverlayOptions initOptions(int res) {
        View view = View.inflate(this, R.layout.layout_location_marker, null);
        ImageView imgMarker = (ImageView) view.findViewById(R.id.img_marker);
        imgMarker.setImageResource(res);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
                .fromView(view);
        return new MarkerOptions().icon(bitmapDescriptor).zIndex(9)
                .draggable(false);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mLocationReceiver != null) {
            unregisterReceiver(mLocationReceiver);
            mLocationReceiver = null;
        }
    }

    private void requestMt() {
        String server = getConfig().getServer() + NetConstant.GET_MTTYPE_LIST;
        String request = Constant.EMPTY_REQUEST;

        NetTask netTask = new NetTask(server, request) {
            @Override
            protected void onResponse(NetTask task, String result) {
                MaintenanceTypeResponse response = MaintenanceTypeResponse.getResult(result);
                mTypeInfos = response.getBody();
                initFloatView();
            }
        };

        addTask(netTask);
    }

    private void initView() {
        initMapView();

        floatView = findViewById(R.id.ll_float);

        tvAddress = (TextView) findViewById(R.id.tv_address);

        if (isLogin()) {
            markAddress(getConfig().getLat(), getConfig().getLng());
            tvAddress.setText(getConfig().getAddress());
        } else {

            mLocationReceiver = new MyLocationReceiver();

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Constant.ACTION_LOCATION_COMPLETE);
            registerReceiver(mLocationReceiver, intentFilter);

            startService(new Intent(this, LocationService.class));

        }
    }
    private void initFloatView() {

        floatView.setVisibility(View.VISIBLE);


        view1 = findViewById(R.id.ll_level1);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1Sel();
            }
        });

        view2 = findViewById(R.id.ll_level2);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view2Sel();
            }
        });

        view3 = findViewById(R.id.ll_level3);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view3Sel();
            }
        });

        tvLevel1Top = (TextView) findViewById(R.id.tv_level1_top);
        tvLevel1Mid = (TextView) findViewById(R.id.tv_level1_mid);
        tvLevel1Bottom = (TextView) findViewById(R.id.tv_level1_bottom);

        tvLevel2Top = (TextView) findViewById(R.id.tv_level2_top);
        tvLevel2Mid = (TextView) findViewById(R.id.tv_level2_mid);
        tvLevel2Bottom = (TextView) findViewById(R.id.tv_level2_bottom);

        tvLevel3Top = (TextView) findViewById(R.id.tv_level3_top);
        tvLevel3Mid = (TextView) findViewById(R.id.tv_level3_mid);
        tvLevel3Bottom = (TextView) findViewById(R.id.tv_level3_bottom);

        tvDetail = (TextView) findViewById(R.id.tv_detail);

        btnDetail = (TextView) findViewById(R.id.btn_detail);
        btnOrder = (TextView) findViewById(R.id.btn_order);

        for (MtTypeInfo info : mTypeInfos) {

            int type = Integer.parseInt(info.getId());

            if (1 == type) {
                tvLevel1Top.setText("一级管家");
                tvLevel1Mid.setText(info.getName());
                tvLevel1Bottom.setText("￥" + info.getPrice() + "/年");

            } else if (2 == type) {
                tvLevel2Top.setText("二级管家");
                tvLevel2Mid.setText(info.getName());
                tvLevel2Bottom.setText("￥" + info.getPrice() + "/年");

            } else if (3 == type) {
                tvLevel3Top.setText("三级管家");
                tvLevel3Mid.setText(info.getName());
                tvLevel3Bottom.setText("￥" + info.getPrice() + "/次");

            }
        }

        view1Sel();

    }

    private void resetView() {

        view1.setBackgroundColor(getResources().getColor(R.color.white));
        tvLevel1Top.setTextColor(getResources().getColor(R.color.black));
        tvLevel1Mid.setTextColor(getResources().getColor(R.color.black));
        tvLevel1Bottom.setTextColor(getResources().getColor(R.color.black));

        view2.setBackgroundColor(getResources().getColor(R.color.white));
        tvLevel2Top.setTextColor(getResources().getColor(R.color.black));
        tvLevel2Mid.setTextColor(getResources().getColor(R.color.black));
        tvLevel2Bottom.setTextColor(getResources().getColor(R.color.black));

        view3.setBackgroundColor(getResources().getColor(R.color.white));
        tvLevel3Top.setTextColor(getResources().getColor(R.color.black));
        tvLevel3Mid.setTextColor(getResources().getColor(R.color.black));
        tvLevel3Bottom.setTextColor(getResources().getColor(R.color.black));
    }

    private void view1Sel() {
        resetView();

        view1.setBackgroundColor(getResources().getColor(R.color.light_grey));
        tvLevel1Top.setTextColor(getResources().getColor(R.color.title_bar));
        tvLevel1Mid.setTextColor(getResources().getColor(R.color.title_bar));
        tvLevel1Bottom.setTextColor(getResources().getColor(R.color.title_bar));

        for (MtTypeInfo info : mTypeInfos) {

            int type = Integer.parseInt(info.getId());

            if (1 == type) {
                tvDetail.setText(info.getContent());
                String content = info.getContent();
                setDetail(content);
                setOrder(info);
                break;
            }
        }
    }

    private void view2Sel() {
        resetView();

        view2.setBackgroundColor(getResources().getColor(R.color.light_grey));
        tvLevel2Top.setTextColor(getResources().getColor(R.color.title_bar));
        tvLevel2Mid.setTextColor(getResources().getColor(R.color.title_bar));
        tvLevel2Bottom.setTextColor(getResources().getColor(R.color.title_bar));

        for (MtTypeInfo info : mTypeInfos) {

            int type = Integer.parseInt(info.getId());

            if (2 == type) {
                tvDetail.setText(info.getContent());

                String content = info.getContent();
                setDetail(content);
                setOrder(info);
                break;
            }
        }
    }

    private void view3Sel() {
        resetView();

        view3.setBackgroundColor(getResources().getColor(R.color.light_grey));
        tvLevel3Top.setTextColor(getResources().getColor(R.color.title_bar));
        tvLevel3Mid.setTextColor(getResources().getColor(R.color.title_bar));
        tvLevel3Bottom.setTextColor(getResources().getColor(R.color.title_bar));

        for (MtTypeInfo info : mTypeInfos) {

            int type = Integer.parseInt(info.getId());

            if (3 == type) {
                tvDetail.setText(info.getContent());
                String content = info.getContent();
                setDetail(content);
                setOrder(info);
                break;
            }
        }
    }


    private void setDetail(final String content) {
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NearMaintenanceActivity.this, MtDetailActivity.class);
                intent.putExtra("data", content);
                startActivity(intent);
            }
        });
    }

    private void setOrder(final MtTypeInfo info) {
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(getConfig().getUserId())) {
                    Intent intent = new Intent(NearMaintenanceActivity.this, AddMtOrderActivity.class);
                    intent.putExtra("data", info);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(NearMaintenanceActivity.this, AddMtOrder2Activity.class);
                    intent.putExtra("data", info);
                    startActivity(intent);
                }
            }
        });

    }

    class MyLocationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean success = intent.getBooleanExtra("success", false);

            Log.d("AAA", "success==>>" + success);

            if (success) {

                double lat = intent.getDoubleExtra("lat", 0);
                double lng = intent.getDoubleExtra("lng", 0);
                String address = intent.getStringExtra("address");
                markAddress(lat, lng);

                tvAddress.setText(address);

            } else {
                showToast("无法获取当前位置，请稍后再试");
            }
        }
    }
}
