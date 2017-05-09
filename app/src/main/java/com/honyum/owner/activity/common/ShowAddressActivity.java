package com.honyum.owner.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;

public class ShowAddressActivity extends BaseActivity {

    private BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_address);

        initView();

        initMapView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBaiduMap.clear();
        markMtPro(mBaiduMap);
        ((TextView) findViewById(R.id.tv_address)).setText(getConfig().getAddress());
    }

    private void initMapView() {
        MapView mMapView = (MapView) findViewById(R.id.mapView);
        mBaiduMap = mMapView.getMap();
    }

    private void markMtPro(BaiduMap mBaiduMap) {

        LatLng point = new LatLng(getConfig().getLat(), getConfig().getLng());

        MapStatusUpdate update = MapStatusUpdateFactory.newLatLngZoom(point, 18);
        mBaiduMap.animateMapStatus(update);

        MarkerOptions options = (MarkerOptions) initOptions(R.mipmap.marker_center);
        options.position(point);

        mBaiduMap.addOverlay(options);
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

    private void initView() {
        initTitleBar(R.id.title, "我的地址", R.mipmap.back, backClickListener, 0, null);
        View view = findViewById(R.id.title);
        TextView tvRight = (TextView) view.findViewById(R.id.tv_right);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("修改");
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowAddressActivity.this, EditCellAddressActivity.class);
                startActivity(intent);
            }
        });
    }
}
