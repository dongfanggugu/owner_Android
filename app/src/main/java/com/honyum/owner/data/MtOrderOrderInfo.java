package com.honyum.owner.data;

import com.honyum.owner.net.base.ResponseBody;

/**
 * Created by LiYouGui on 2017/5/8.
 */

public class MtOrderOrderInfo extends ResponseBody {


    /**
     * code : 20170508112750
     * createTime : 2017-05-08 11:27:50
     * frequency : 1
     * id : c218f7cd-574f-4c87-b623-f53f7355b746
     * maintOrderId : d9a4b9d3-5c5f-4373-81f7-e1923387fb59
     * maintOrderInfo : {"code":"20170505150208","createTime":"2017-05-05 15:02:08","id":"d9a4b9d3-5c5f-4373-81f7-e1923387fb59","isDelete":"0","isPay":"0","price":0}
     * mainttypeId : 1
     * mainttypeInfo : {"content":"按次收费","createTime":"2017-03-02 10:45:21","id":"1","name":"单次服务","price":0.01}
     * orderid : b2de0f42-5d09-4279-9c95-f759d28d8eed
     * payMoney : 0.01
     * smallOwnerId : f4522833-364e-49c5-9980-ddb1a6dec7d8
     * smallOwnerInfo : {"address":"三里屯太古里","brand":"奥蒂斯","cellName":"SOHO","createTime":"2017-03-06 13:36:20","id":"f4522833-364e-49c5-9980-ddb1a6dec7d8","isDelete":"0","lat":39.94009,"lng":116.4611,"model":"model2","name":"张长浩","password":"e10adc3949ba59abbe56e057f20f883e","tel":"18513831372"}
     * type : 1
     */

    private String code;
    private String createTime;
    private int frequency;
    private String id;
    private String maintOrderId;
    private MaintOrderInfoBean maintOrderInfo;
    private String mainttypeId;
    private MtTypeInfo mainttypeInfo;
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

    public String getMaintOrderId() {
        return maintOrderId;
    }

    public void setMaintOrderId(String maintOrderId) {
        this.maintOrderId = maintOrderId;
    }

    public MaintOrderInfoBean getMaintOrderInfo() {
        return maintOrderInfo;
    }

    public void setMaintOrderInfo(MaintOrderInfoBean maintOrderInfo) {
        this.maintOrderInfo = maintOrderInfo;
    }

    public String getMainttypeId() {
        return mainttypeId;
    }

    public void setMainttypeId(String mainttypeId) {
        this.mainttypeId = mainttypeId;
    }

    public MtTypeInfo getMainttypeInfo() {
        return mainttypeInfo;
    }

    public void setMainttypeInfo(MtTypeInfo mainttypeInfo) {
        this.mainttypeInfo = mainttypeInfo;
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

    public static class MaintOrderInfoBean {
        /**
         * code : 20170505150208
         * createTime : 2017-05-05 15:02:08
         * id : d9a4b9d3-5c5f-4373-81f7-e1923387fb59
         * isDelete : 0
         * isPay : 0
         * price : 0
         */

        private String code;
        private String createTime;
        private String id;
        private String isDelete;
        private String isPay;
        private int price;

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

        public String getIsPay() {
            return isPay;
        }

        public void setIsPay(String isPay) {
            this.isPay = isPay;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
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
