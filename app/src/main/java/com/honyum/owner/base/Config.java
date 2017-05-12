package com.honyum.owner.base;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.honyum.owner.utils.Utils;

import java.io.Serializable;

/**
 * 保存配置信息，如一页显示信息条数
 */
public class Config implements Serializable {


    private SharedPreferences mPreferences;

    private Editor mEditor;

    public Config(Context context) {
        mPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public void setIp(String ip) {
        mEditor.putString("ip", ip);
        mEditor.commit();
    }

    public String getIp() {
        return mPreferences.getString("ip", "http://47.93.11.158:8081/");

      //  return mPreferences.getString("ip", "http://192.168.0.82:8080/");
    }
//    public String getIp() {
//        return mPreferences.getString("ip", "192.168.0.82");
//    }

    /**
     * 获取服务器地址
     */
    public String getServer() {
        return getIp() + "mobile/";
    }

    /**
     * 保存用户token
     */
    public void setToken(String token) {
        mEditor.putString("token", token);
        mEditor.commit();
    }

    /**
     * 获取用户token
     */
    public String getToken() {
        return mPreferences.getString("token", "test");
    }

    public void setUserName(String userName) {
        mEditor.putString("userName", userName);
        mEditor.commit();
    }

    public String getUserName() {
        return mPreferences.getString("userName", "");
    }

    public void setLoginUserName(String loginUserName) {
        mEditor.putString("loginUserName", loginUserName);
        mEditor.commit();
    }

    public String getLoginUserName() {
        return mPreferences.getString("loginUserName", "");
    }

    public void setPassword(String password) {
        mEditor.putString("password", password);
        mEditor.commit();
    }

    public String getPassword() {
        return mPreferences.getString("password", "");
    }

    public void setUserId(String userId) {
        mEditor.putString("userId", userId);
        mEditor.commit();
    }

    public String getUserId() {
        return mPreferences.getString("userId", "");
    }

    public void setName(String name) {
        mEditor.putString("name", name);
        mEditor.commit();
    }

    public String getName() {
        return mPreferences.getString("name", "");
    }

    public void setTel(String tel) {
        mEditor.putString("tel", tel);
        mEditor.commit();
    }

    public String getTel() {
        return mPreferences.getString("tel", "");
    }

    public void setLat(double lat) {
        mEditor.putString("lat", "" + lat);
        mEditor.commit();
    }

    public double getLat() {
        return Utils.getDouble(mPreferences.getString("lat", ""));
    }

    public void setLng(double lng) {
        mEditor.putString("lng", "" + lng);
        mEditor.commit();
    }

    public double getLng() {
        return Utils.getDouble(mPreferences.getString("lng", ""));
    }

    public void setAddress(String address) {
        mEditor.putString("address", address);
        mEditor.commit();
    }

    public String getAddress() {
        return mPreferences.getString("address", "");
    }

    public void setBrand(String brand) {
        mEditor.putString("brand", brand);
        mEditor.commit();
    }

    public String getBrand() {
        return mPreferences.getString("brand", "");
    }

    public void setModel(String model) {
        mEditor.putString("model", model);
        mEditor.commit();
    }

    public String getModel() {
        return mPreferences.getString("model", "");
    }

    public void setCellName(String cellName) {
        mEditor.putString("cellName", cellName);
        mEditor.commit();
    }

    public String getCellName() {
        return mPreferences.getString("cellName", "");
    }

    public void setRunning(boolean running) {
        mEditor.putBoolean("running", running);
        mEditor.commit();
    }

    public boolean isRunning() {
        return mPreferences.getBoolean("running", false);
    }
}
