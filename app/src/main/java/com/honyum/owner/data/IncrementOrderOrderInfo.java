package com.honyum.owner.data;

import com.honyum.owner.net.base.ResponseBody;

/**
 * Created by LiYouGui on 2017/5/8.
 */

public class IncrementOrderOrderInfo extends ResponseBody {


    /**
     * code : 20170505155522
     * createTime : 2017-05-05 15:55:22
     * frequency : 1
     * id : 3acd6d0f-a564-4d64-bf92-e0bfe809fa97
     * incrementId : d7fff080-5255-4c5f-a400-9a83718a9818
     * incrementInfo : {"code":"20170505152104","createTime":"2017-05-05 15:21:04","expireTime":"2020-05-05 16:34:51","frequency":0,"id":"d7fff080-5255-4c5f-a400-9a83718a9818","isDelete":"0"}
     * incrementTypeId : b77c4c58-a0fe-42ea-855d-db6ee8d68ed1
     * incrementTypeInfo : {"content":"24小时监控您的电梯运行状态","createTime":"2017-05-05 14:56:11","id":"b77c4c58-a0fe-42ea-855d-db6ee8d68ed1","logo":"27f18342-92c9-499a-b208-1dca9d69b1e9.png","name":"智能小助手","price":0.01}
     * isPay : 1
     * orderid : a8bcb875-ef9c-455e-bb45-4e6fe99d08be
     * payMoney : 0.01
     * payTime : 2017-05-05 16:34:51
     * smallOwnerId : f4522833-364e-49c5-9980-ddb1a6dec7d8
     * smallOwnerInfo : {"address":"三里屯太古里","brand":"奥蒂斯","cellName":"SOHO","createTime":"2017-03-06 13:36:20","id":"f4522833-364e-49c5-9980-ddb1a6dec7d8","isDelete":"0","lat":39.94009,"lng":116.4611,"model":"model2","name":"张长浩","password":"e10adc3949ba59abbe56e057f20f883e","tel":"18513831372"}
     * type : 3
     */

    private String code;
    private String createTime;
    private int frequency;
    private String id;
    private String incrementId;
    private IncrementInfoBean incrementInfo;
    private String incrementTypeId;
    private IncrementTypeInfoBean incrementTypeInfo;
    private String isPay;
    private String orderid;
    private double payMoney;
    private String payTime;
    private String smallOwnerId;
    private SmallOwnerInfoBean smallOwnerInfo;
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncrementId() {
        return incrementId;
    }

    public void setIncrementId(String incrementId) {
        this.incrementId = incrementId;
    }

    public IncrementInfoBean getIncrementInfo() {
        return incrementInfo;
    }

    public void setIncrementInfo(IncrementInfoBean incrementInfo) {
        this.incrementInfo = incrementInfo;
    }

    public String getIncrementTypeId() {
        return incrementTypeId;
    }

    public void setIncrementTypeId(String incrementTypeId) {
        this.incrementTypeId = incrementTypeId;
    }

    public IncrementTypeInfoBean getIncrementTypeInfo() {
        return incrementTypeInfo;
    }

    public void setIncrementTypeInfo(IncrementTypeInfoBean incrementTypeInfo) {
        this.incrementTypeInfo = incrementTypeInfo;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getSmallOwnerId() {
        return smallOwnerId;
    }

    public void setSmallOwnerId(String smallOwnerId) {
        this.smallOwnerId = smallOwnerId;
    }

    public SmallOwnerInfoBean getSmallOwnerInfo() {
        return smallOwnerInfo;
    }

    public void setSmallOwnerInfo(SmallOwnerInfoBean smallOwnerInfo) {
        this.smallOwnerInfo = smallOwnerInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class IncrementInfoBean {
        /**
         * code : 20170505152104
         * createTime : 2017-05-05 15:21:04
         * expireTime : 2020-05-05 16:34:51
         * frequency : 0
         * id : d7fff080-5255-4c5f-a400-9a83718a9818
         * isDelete : 0
         */

        private String code;
        private String createTime;
        private String expireTime;
        private int frequency;
        private String id;
        private String isDelete;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }
    }

    public static class IncrementTypeInfoBean {
        /**
         * content : 24小时监控您的电梯运行状态
         * createTime : 2017-05-05 14:56:11
         * id : b77c4c58-a0fe-42ea-855d-db6ee8d68ed1
         * logo : 27f18342-92c9-499a-b208-1dca9d69b1e9.png
         * name : 智能小助手
         * price : 0.01
         */

        private String content;
        private String createTime;
        private String id;
        private String logo;
        private String name;
        private double price;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    public static class SmallOwnerInfoBean {
        /**
         * address : 三里屯太古里
         * brand : 奥蒂斯
         * cellName : SOHO
         * createTime : 2017-03-06 13:36:20
         * id : f4522833-364e-49c5-9980-ddb1a6dec7d8
         * isDelete : 0
         * lat : 39.94009
         * lng : 116.4611
         * model : model2
         * name : 张长浩
         * password : e10adc3949ba59abbe56e057f20f883e
         * tel : 18513831372
         */

        private String address;
        private String brand;
        private String cellName;
        private String createTime;
        private String id;
        private String isDelete;
        private double lat;
        private double lng;
        private String model;
        private String name;
        private String password;
        private String tel;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
