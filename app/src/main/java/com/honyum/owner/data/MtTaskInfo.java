package com.honyum.owner.data;

import com.honyum.owner.net.base.ResponseBody;

import java.io.Serializable;

/**
 * Created by 李有鬼 on 2017/3/2 0002
 */
public class MtTaskInfo extends ResponseBody {

    /**
     * id : 054b92c7-37c4-491d-b0dd-8e6db5118a6e
     * isPay : 0
     * maintOrderInfo : {"code":"20170224150606","createTime":"2017-02-24 15:06:05","id":"d06d2182-00f2-437d-b041-959f58850e21","isPay":"0","maintypeName":"全包","ownerId":"75b41df9-910d-411a-9b7e-29485047ab7c","ownerInfo":{"address":"TTYSQ","brand":"DZ","cellName":"TTY","createTime":"2017-02-24 11:07:01","id":"75b41df9-910d-411a-9b7e-29485047ab7c","isDelete":"0","loginname":"zhangsan","model":"DZ","name":"zhang","openid":"18516965274","password":"e10adc3949ba59abbe56e057f20f883e","sex":"1","tel":"13800138000"},"price":8000}
     * maintUserId : 1c49f397-040b-4a0e-a579-706845595b1d
     * maintUserInfo : {"age":18,"code":"222","createdatetime":1433931566000,"id":"1c49f397-040b-4a0e-a579-706845595b1d","isDelete":"0","isdefault":1,"loginname":"work2","name":"张三","operationCard":"12345678","password":"e10adc3949ba59abbe56e057f20f883e","pic":"1483000347620.jpg","remark":"","sex":1,"state":1,"tel":"13800138000","type":"3","usertype":0}
     * planTime : 2017-02-22
     * state : 0
     * taskCode : 20170224180508
     */

    private String id;
    private String isPay;
    private MaintOrderInfoBean maintOrderInfo;
    private String maintUserId;
    private MaintUserInfoBean maintUserInfo;

    private String planTime;
    private String state;
    private String taskCode;
    private String stateStr;

    /**
     * maintOrderProcessId : 75b41df9-910d-411a-9b7e-29485047ab7c
     * evaluateContent :
     * evaluateResult : 5
     */

    private String maintOrderProcessId;
    private String evaluateContent;
    private int evaluateResult;


    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
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

    public MaintOrderInfoBean getMaintOrderInfo() {
        return maintOrderInfo;
    }

    public void setMaintOrderInfo(MaintOrderInfoBean maintOrderInfo) {
        this.maintOrderInfo = maintOrderInfo;
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


    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getMaintOrderProcessId() {
        return maintOrderProcessId;
    }

    public void setMaintOrderProcessId(String maintOrderProcessId) {
        this.maintOrderProcessId = maintOrderProcessId;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public int getEvaluateResult() {
        return evaluateResult;
    }

    public void setEvaluateResult(int evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    public static class MaintOrderInfoBean implements Serializable {
        /**
         * code : 20170224150606
         * createTime : 2017-02-24 15:06:05
         * id : d06d2182-00f2-437d-b041-959f58850e21
         * isPay : 0
         * maintypeName : 全包
         * ownerId : 75b41df9-910d-411a-9b7e-29485047ab7c
         * ownerInfo : {"address":"TTYSQ","brand":"DZ","cellName":"TTY","createTime":"2017-02-24 11:07:01","id":"75b41df9-910d-411a-9b7e-29485047ab7c","isDelete":"0","loginname":"zhangsan","model":"DZ","name":"zhang","openid":"18516965274","password":"e10adc3949ba59abbe56e057f20f883e","sex":"1","tel":"13800138000"}
         * price : 8000
         */

        private String code;
        private String createTime;
        private String id;
        private String isPay;
        private String maintypeName;

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

        public MtTypeInfo getMaintypeInfo() {
            return maintypeInfo;
        }

        public void setMaintypeInfo(MtTypeInfo maintypeInfo) {
            this.maintypeInfo = maintypeInfo;
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

    public static class MaintUserInfoBean implements Serializable {
        /**
         * age : 18
         * code : 222
         * createdatetime : 1433931566000
         * id : 1c49f397-040b-4a0e-a579-706845595b1d
         * isDelete : 0
         * isdefault : 1
         * loginname : work2
         * name : 张三
         * operationCard : 12345678
         * password : e10adc3949ba59abbe56e057f20f883e
         * pic : 1483000347620.jpg
         * remark :
         * sex : 1
         * state : 1
         * tel : 13800138000
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
        private String pic;
        private String remark;
        private int sex;
        private int state;
        private String tel;
        private String type;
        private int usertype;
        private String stateStr;

        public String getStateStr() {
            return stateStr;
        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
        }

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
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
}
