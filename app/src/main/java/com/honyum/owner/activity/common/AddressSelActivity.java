package com.honyum.owner.activity.common;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.honyum.owner.R;
import com.honyum.owner.base.BaseActivity;
import com.honyum.owner.base.Constant;
import com.honyum.owner.common.service.LocationService;

import java.util.ArrayList;
import java.util.List;


public class AddressSelActivity extends BaseActivity {

    private static final int MAP_ADDRESS = 0;

    private static final int SEARCH_ADDRESS = 1;

    private MyLocationReceiver mReceiver;

    private BaiduMap mBaiduMap;

    private GeoCoder geoCoder;

    private PoiSearch poiSearch;

    private LocationService.MyBinder myBinder;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (LocationService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private MyAdapter adapter;

    private List<PoiInfo> poiInfos;

    private TextView tvCity;

    private RecyclerView searchRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_sel);

        initView();

        initMap();

        Intent intent = new Intent(this, LocationService.class);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);

        mReceiver = new MyLocationReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_LOCATION_COMPLETE);
        registerReceiver(mReceiver, intentFilter);
    }

    private void initMap() {
        MapView mMapView = (MapView) findViewById(R.id.mapView);
        mBaiduMap = mMapView.getMap();

        //隐藏百度logo
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        //隐藏错放按钮
        mMapView.showZoomControls(false);

        geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(geoListener);

        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(poiListener);

        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                geoCoder.reverseGeoCode(new ReverseGeoCodeOption()
                        .location(new LatLng(mapStatus.target.latitude, mapStatus.target.longitude))
                );
            }
        });
    }

    OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                MyAdapter adapter = new MyAdapter(poiResult.getAllPoi(), SEARCH_ADDRESS);
                searchRecycle.setAdapter(adapter);
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };

    OnGetGeoCoderResultListener geoListener = new OnGetGeoCoderResultListener() {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
            tvCity.setText(reverseGeoCodeResult.getAddressDetail().city);
            poiInfos.clear();
            poiInfos.addAll(reverseGeoCodeResult.getPoiList());
            adapter.addData(poiInfos);
        }
    };


    private void initView() {

        tvCity = (TextView) findViewById(R.id.tv_city);

        searchRecycle = (RecyclerView) findViewById(R.id.searchRecycler);
        searchRecycle.setLayoutManager(new LinearLayoutManager(this));

        final EditText etSearch = (EditText) findViewById(R.id.add_search);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchRecycle.getVisibility() == View.GONE) {
                    finish();
                } else {
                    searchRecycle.setVisibility(View.GONE);

                    MyAdapter adapter = (MyAdapter) searchRecycle.getAdapter();
                    if (adapter != null) {
                        adapter.clear();
                    }

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                }
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        poiInfos = new ArrayList<>();
        adapter = new MyAdapter(poiInfos, MAP_ADDRESS);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.iv_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myBinder != null) {
                    myBinder.getService().requestLocation();
                }
            }
        });

        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchRecycle.setVisibility(View.VISIBLE);
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                poiSearch.searchInCity(new PoiCitySearchOption()
                        .city(tvCity.getText().toString())
                        .keyword(s.toString())
                );
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                PoiInfo item = (PoiInfo) baseQuickAdapter.getItem(i);

                if (item.location.latitude == 0.0 || item.location.longitude == 0.0) {
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("address_name", item.name);
                intent.putExtra("latlng", new double[]{item.location.latitude, item.location.longitude});
                setResult(0, intent);
                finish();
            }
        });

        searchRecycle.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                PoiInfo item = (PoiInfo) baseQuickAdapter.getItem(i);

                if (item.location.latitude == 0.0 || item.location.longitude == 0.0) {
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("address_name", item.name);
                intent.putExtra("latlng", new double[]{item.location.latitude, item.location.longitude});
                setResult(0, intent);
                finish();
            }
        });
    }


    class MyAdapter extends BaseQuickAdapter<PoiInfo> {

        private int type;

        private List<PoiInfo> data;

        MyAdapter(List<PoiInfo> data, int type) {
            super(R.layout.layout_addsel_item, data);
            this.data = data;
            this.type = type;
        }

        @Override
        protected void convert(BaseViewHolder vh, PoiInfo poiInfo) {

            TextView tvName = (TextView) vh.convertView.findViewById(R.id.tv_name);

            if (vh.getAdapterPosition() == 0 && type == MAP_ADDRESS) {
                tvName.setText("[当前位置]" + poiInfo.name + "");
                tvName.getPaint().setFakeBoldText(true);
            } else {
                tvName.setText(poiInfo.name);
                tvName.getPaint().setFakeBoldText(false);
            }

            vh.setText(R.id.tv_address, poiInfo.address);
        }

        void clear() {
            if (data != null) {
                data.clear();
                notifyDataSetChanged();
            }
        }
    }

    class MyLocationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean success = intent.getBooleanExtra("success", false);

            Log.d("AAA", "success==>>" + success);

            if (success) {
                mBaiduMap.clear();

                double lat = intent.getDoubleExtra("lat", 0);
                double lng = intent.getDoubleExtra("lng", 0);

                LatLng latLng = new LatLng(lat, lng);

                MapStatusUpdate update = MapStatusUpdateFactory.newLatLngZoom(latLng, 18);
                mBaiduMap.animateMapStatus(update);

                OverlayOptions options = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.movie_shop_address_icon))
                        .zIndex(9)
                        .draggable(false);
                mBaiduMap.addOverlay(options);

                geoCoder.reverseGeoCode(new ReverseGeoCodeOption()
                        .location(latLng)
                );
            } else {
                showToast("无法获取当前位置，请稍后再试");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
        unregisterReceiver(mReceiver);
        poiSearch.destroy();
        geoCoder.destroy();
    }
}
