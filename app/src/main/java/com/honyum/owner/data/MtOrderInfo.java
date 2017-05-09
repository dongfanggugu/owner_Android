package com.honyum.owner.data;

import com.honyum.owner.net.base.ResponseBody;

import java.io.Serializable;

/**
 * Created by 李有鬼 on 2017/3/1 0001
 */
public class MtOrderInfo extends ResponseBody {


    /**
     * code : 20170224110701
     * createTime : 2017-02-24 11:07:01
     * id : 429aee2a-68d8-43a2-8b39-966762fa6fec
     * isPay : 0
     * mainttypeId : ad93ae56-8440-4b03-88f5-1fc22399df95
     * maintypeName : 全包
     * ownerId : 75b41df9-910d-411a-9b7e-29485047ab7c
     * ownerInfo : {"address":"TTYSQ","brand":"DZ","cellName":"TTY","createTime":"2017-02-24 11:07:01","id":"75b41df9-910d-411a-9b7e-29485047ab7c","isDelete":"0","loginname":"zhangsan","model":"DZ","name":"zhang","openid":"18516965274","password":"e10adc3949ba59abbe56e057f20f883e","sex":"1","tel":"13800138000"}
     * price : 8000
     */

    private String code;
    private String createTime;
    private String id;
    private String isPay;
    private String mainttypeId;
    private String maintypeName;
    private String mainttypeName;
    private String ownerId;
    private int frequency;
    private String expireTime;
    private OwnerInfoBean ownerInfo;
    private int price;
    /**
     * maintypeInfo : {"content":"按次收费","createTime":"2017-03-02 10:45:21","id":"1","name":"单次服务","price":0.01}
     */

    private MtTypeInfo maintypeInfo;


    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public MtTypeInfo getMaintypeInfo() {
        return maintypeInfo;
    }

    public void setMaintypeInfo(MtTypeInfo maintypeInfo) {
        this.maintypeInfo = maintypeInfo;
    }

    public String getMainttypeName() {
        return mainttypeName;
    }

    public void setMainttypeName(String mainttypeName) {
        this.mainttypeName = mainttypeName;
    }

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

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public String getMainttypeId() {
        return mainttypeId;
    }

    public void setMainttypeId(String mainttypeId) {
        this.mainttypeId = mainttypeId;
    }

    public String getMaintypeName() {
        return maintypeName;
    }

    public void setMaintypeName(String maintypeName) {
        this.maintypeName = maintypeName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public OwnerInfoBean getOwnerInfo() {
        return ownerInfo;
    }

    public void setOwnerInfo(OwnerInfoBean ownerInfo) {
        this.ownerInfo = ownerInfo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public static class OwnerInfoBean implements Serializable {
        /**
         * address : TTYSQ
         * brand : DZ
         * cellName : TTY
         * createTime : 2017-02-24 11:07:01
         * id : 75b41df9-910d-411a-9b7e-29485047ab7c
         * isDelete : 0
         * loginname : zhangsan
         * model : DZ
         * name : zhang
         * openid : 18516965274
         * password : e10adc3949ba59abbe56e057f20f883e
         * sex : 1
         * tel : 13800138000
         */

        private String address;
        private String brand;
        private String cellName;
        private String createTime;
        private String id;
        private String isDelete;
        private String loginname;
        private String model;
        private String name;
        private String openid;
        private String password;
        private String sex;
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

        public String getLoginname() {
            return loginname;
        }

        public void setLoginname(String loginname) {
            this.loginname = loginname;
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

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
