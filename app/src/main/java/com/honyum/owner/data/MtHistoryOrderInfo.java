package com.honyum.owner.data;

import com.honyum.owner.net.base.ResponseBody;

/**
 * Created by LiYouGui on 2017/5/8.
 */

public class MtHistoryOrderInfo extends ResponseBody {


    /**
     * code : 20170505150208
     * createTime : 2017-05-05 15:02:08
     * deleteTime : 2017-05-08 14:39:10
     * deleteUserId : 1de892d7-6fff-4841-8371-3e961cdd05df
     * deleteUserInfo : {"age":22,"code":"1","createdatetime":1488778127000,"id":"1de892d7-6fff-4841-8371-3e961cdd05df","isDelete":"0","isdefault":0,"loginname":"dutyTest","name":"duty","password":"e10adc3949ba59abbe56e057f20f883e","remark":"","sex":1,"state":1,"tel":"15802929795","type":"5","usertype":0}
     * id : d9a4b9d3-5c5f-4373-81f7-e1923387fb59
     * isDelete : 1
     * isPay : 0
     * maintUserId : 8fe42e81-2439-44c4-b503-c285094dd65a
     * maintUserInfo : {"age":0,"code":"2","createdatetime":1433738655000,"id":"8fe42e81-2439-44c4-b503-c285094dd65a","isDelete":"0","isdefault":1,"loginname":"武昌子","name":"武昌子","operationCard":"","password":"e10adc3949ba59abbe56e057f20f883e","remark":"","sex":1,"state":1,"tel":"13691108854","type":"3","usertype":0}
     * mainttypeId : 1
     * mainttypeName : 单次服务
     * maintypeInfo : {"content":"按次收费","createTime":"2017-03-02 10:45:21","id":"1","name":"单次服务","price":0.01}
     * ownerId : f4522833-364e-49c5-9980-ddb1a6dec7d8
     * ownerInfo : {"address":"三里屯太古里","brand":"奥蒂斯","cellName":"SOHO","createTime":"2017-03-06 13:36:20","id":"f4522833-364e-49c5-9980-ddb1a6dec7d8","isDelete":"0","lat":39.94009,"lng":116.4611,"model":"model2","name":"张长浩","password":"e10adc3949ba59abbe56e057f20f883e","tel":"18513831372"}
     * price : 0
     */

    private String code;
    private String createTime;
    private String deleteTime;
    private String deleteUserId;
    private DeleteUserInfoBean deleteUserInfo;
    private String id;
    private String isDelete;
    private String isPay;
    private String maintUserId;
    private MaintUserInfoBean maintUserInfo;
    private String mainttypeId;
    private String mainttypeName;
    private MtTypeInfo maintypeInfo;
    private String ownerId;
    private OwnerInfoBean ownerInfo;
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

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public DeleteUserInfoBean getDeleteUserInfo() {
        return deleteUserInfo;
    }

    public void setDeleteUserInfo(DeleteUserInfoBean deleteUserInfo) {
        this.deleteUserInfo = deleteUserInfo;
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

    public String getMaintUserId() {
        return maintUserId;
    }

    public void setMaintUserId(String maintUserId) {
        this.maintUserId = maintUserId;
    }

    public MaintUserInfoBean getMaintUserInfo() {
        return maintUserInfo;
    }

    public void setMaintUserInfo(MaintUserInfoBean maintUserInfo) {
        this.maintUserInfo = maintUserInfo;
    }

    public String getMainttypeId() {
        return mainttypeId;
    }

    public void setMainttypeId(String mainttypeId) {
        this.mainttypeId = mainttypeId;
    }

    public String getMainttypeName() {
        return mainttypeName;
    }

    public void setMainttypeName(String mainttypeName) {
        this.mainttypeName = mainttypeName;
    }

    public MtTypeInfo getMaintypeInfo() {
        return maintypeInfo;
    }

    public void setMaintypeInfo(MtTypeInfo maintypeInfo) {
        this.maintypeInfo = maintypeInfo;
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

    public static class DeleteUserInfoBean {
        /**
         * age : 22
         * code : 1
         * createdatetime : 1488778127000
         * id : 1de892d7-6fff-4841-8371-3e961cdd05df
         * isDelete : 0
         * isdefault : 0
         * loginname : dutyTest
         * name : duty
         * password : e10adc3949ba59abbe56e057f20f883e
         * remark :
         * sex : 1
         * state : 1
         * tel : 15802929795
         * type : 5
         * usertype : 0
         */

        private int age;
        private String code;
        private long createdatetime;
        private String id;
        private String isDelete;
        private int isdefault;
        private String loginname;
        private String name;
        private String password;
        private String remark;
        private int sex;
        private int state;
        private String tel;
        private String type;
        private int usertype;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getCreatedatetime() {
            return createdatetime;
        }

        public void setCreatedatetime(long createdatetime) {
            this.createdatetime = createdatetime;
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

        public int getIsdefault() {
            return isdefault;
        }

        public void setIsdefault(int isdefault) {
            this.isdefault = isdefault;
        }

        public String getLoginname() {
            return loginname;
        }

        public void setLoginname(String loginname) {
            this.loginname = loginname;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getUsertype() {
            return usertype;
        }

        public void setUsertype(int usertype) {
            this.usertype = usertype;
        }
    }

    public static class MaintUserInfoBean {
        /**
         * age : 0
         * code : 2
         * createdatetime : 1433738655000
         * id : 8fe42e81-2439-44c4-b503-c285094dd65a
         * isDelete : 0
         * isdefault : 1
         * loginname : 武昌子
         * name : 武昌子
         * operationCard :
         * password : e10adc3949ba59abbe56e057f20f883e
         * remark :
         * sex : 1
         * state : 1
         * tel : 13691108854
         * type : 3
         * usertype : 0
         */

        private int age;
        private String code;
        private long createdatetime;
        private String id;
        private String isDelete;
        private int isdefault;
        private String loginname;
        private String name;
        private String operationCard;
        private String password;
        private String remark;
        private int sex;
        private int state;
        private String tel;
        private String type;
        private int usertype;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getCreatedatetime() {
            return createdatetime;
        }

        public void setCreatedatetime(long createdatetime) {
            this.createdatetime = createdatetime;
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

        public int getIsdefault() {
            return isdefault;
        }

        public void setIsdefault(int isdefault) {
            this.isdefault = isdefault;
        }

        public String getLoginname() {
            return loginname;
        }

        public void setLoginname(String loginname) {
            this.loginname = loginname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOperationCard() {
            return operationCard;
        }

        public void setOperationCard(String operationCard) {
            this.operationCard = operationCard;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getUsertype() {
            return usertype;
        }

        public void setUsertype(int usertype) {
            this.usertype = usertype;
        }
    }

    public static class OwnerInfoBean {
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
