package com.honyum.owner.data;

import com.honyum.owner.net.base.RequestBody;

/**
 * Created by 李有鬼 on 2017/5/6 0006.
 */

public class AddIncrementReqBody extends RequestBody {

    private String tel;
    private String name;
    private String sex;
    private String brand;
    private String model;
    private String cellName;
    private String address;
    private String loginname;
    private String incrementTypeId;
    private double lat;
    private double lng;
    //购买数量
    private int frequency;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getIncrementTypeId() {
        return incrementTypeId;
    }

    public void setIncrementTypeId(String incrementTypeId) {
        this.incrementTypeId = incrementTypeId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
