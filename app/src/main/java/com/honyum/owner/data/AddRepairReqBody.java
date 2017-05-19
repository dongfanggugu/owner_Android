package com.honyum.owner.data;

import com.honyum.owner.net.base.RequestBody;

/**
 * Created by 李有鬼 on 2017/3/1 0001
 */
public class AddRepairReqBody extends RequestBody {

    /**
     * tel:电话
     * name:姓名
     * sex:性别1男2女
     * brand:电梯品牌
     * model:电梯型号
     * cellName:小区名称
     * address:地址
     * loginname:用户名
     * repairTypeId: 故障类型Id
     * phenomenon:报修现象
     * repairTime:维修时间
     */

    private String tel;
    private String name;
    private String sex;
    private String brand;
    private String model;
    private String cellName;
    private String address;
    private String loginname;
    private String repairTypeId;
    private String phenomenon;
    private String repairTime;
    private double lat;
    private double lng;

    private String contacts;

    private String contactsTel;

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

    public String getRepairTypeId() {
        return repairTypeId;
    }

    public void setRepairTypeId(String repairTypeId) {
        this.repairTypeId = repairTypeId;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
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

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsTel() {
        return contactsTel;
    }

    public void setContactsTel(String contactsTel) {
        this.contactsTel = contactsTel;
    }
}
