package com.honyum.owner.net;

import com.honyum.owner.net.base.RequestBean;

import java.io.Serializable;

/**
 * Created by 李有鬼 on 2017/1/13 0013
 */

public class EditPersonInfoRequest extends RequestBean {

    private EditPersonInfoReqBody body;

    public EditPersonInfoReqBody getBody() {
        return body;
    }

    public void setBody(EditPersonInfoReqBody body) {
        this.body = body;
    }

    public class EditPersonInfoReqBody implements Serializable {

        private String name;

        private String brand;

        private String cellName;

        private String address;

        private double lat;

        private double lng;

        private String model;

        private String contacts;

        private String contactsTel;

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getCellName() {
            return cellName;
        }

        public void setCellName(String cellName) {
            this.cellName = cellName;
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
}
