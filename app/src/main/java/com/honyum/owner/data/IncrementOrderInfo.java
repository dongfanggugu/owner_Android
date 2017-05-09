package com.honyum.owner.data;

import com.honyum.owner.net.base.ResponseBody;

/**
 * Created by LiYouGui on 2017/5/8.
 */

public class IncrementOrderInfo extends ResponseBody {


    /**
     * code : 20170505152104
     * createTime : 2017-05-05 15:21:04
     * expireTime : 2020-05-05 16:34:51
     * frequency : 0
     * id : d7fff080-5255-4c5f-a400-9a83718a9818
     * incrementTypeId : b77c4c58-a0fe-42ea-855d-db6ee8d68ed1
     * incrementTypeInfo : {"content":"24小时监控您的电梯运行状态","createTime":"2017-05-05 14:56:11","id":"b77c4c58-a0fe-42ea-855d-db6ee8d68ed1","logo":"http://123.57.10.16:8081/static/27f18342-92c9-499a-b208-1dca9d69b1e9.png","name":"智能小助手","price":0.01}
     * incrementTypeName : 智能小助手
     * isDelete : 0
     * smallOwnerId : f4522833-364e-49c5-9980-ddb1a6dec7d8
     * smallOwnerInfo : {"address":"三里屯太古里","brand":"奥蒂斯","cellName":"SOHO","createTime":"2017-03-06 13:36:20","id":"f4522833-364e-49c5-9980-ddb1a6dec7d8","isDelete":"0","lat":39.94009,"lng":116.4611,"model":"model2","name":"张长浩","password":"e10adc3949ba59abbe56e057f20f883e","tel":"18513831372"}
     * smallOwnerName : 张长浩
     * smallOwnerTel : 18513831372
     */

    private String code;
    private String createTime;
    private String expireTime;
    private int frequency;
    private String id;
    private String incrementTypeId;
    private IncrementType incrementTypeInfo;
    private String incrementTypeName;
    private String isDelete;
    private String smallOwnerId;
    private SmallOwnerInfoBean smallOwnerInfo;
    private String smallOwnerName;
    private String smallOwnerTel;

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

    public String getIncrementTypeId() {
        return incrementTypeId;
    }

    public void setIncrementTypeId(String incrementTypeId) {
        this.incrementTypeId = incrementTypeId;
    }

    public IncrementType getIncrementTypeInfo() {
        return incrementTypeInfo;
    }

    public void setIncrementTypeInfo(IncrementType incrementTypeInfo) {
        this.incrementTypeInfo = incrementTypeInfo;
    }

    public String getIncrementTypeName() {
        return incrementTypeName;
    }

    public void setIncrementTypeName(String incrementTypeName) {
        this.incrementTypeName = incrementTypeName;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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

    public String getSmallOwnerName() {
        return smallOwnerName;
    }

    public void setSmallOwnerName(String smallOwnerName) {
        this.smallOwnerName = smallOwnerName;
    }

    public String getSmallOwnerTel() {
        return smallOwnerTel;
    }

    public void setSmallOwnerTel(String smallOwnerTel) {
        this.smallOwnerTel = smallOwnerTel;
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
