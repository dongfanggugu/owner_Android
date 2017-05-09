package com.honyum.owner.data;

import com.honyum.owner.net.base.ResponseBody;

import java.io.Serializable;

/**
 * Created by 李有鬼 on 2017/3/1 0001
 */
public class RepairOrderInfo extends ResponseBody {


    /**
     * id : 报修单ID
     * phenomenon : 报修现象
     * createTime : 报修时间
     * repairTime : 维修时间
     * isDelete : 是否删除
     * state : 状态 1待确认2已确认4已委派6维修中8维修完成9确认完成
     * smallOwnerId : 业主ID
     * name : 业主姓名
     * tel : 业主电话
     * address : 地址
     * brand : 电梯品牌
     * isPayment : 是否支付
     * evaluate : 评价
     * evaluateInfo : 评价内容
     * code : 报修单编号
     * repairTypeId : 报修类型ID
     * repairTypeName : 报修类型名称
     */

    private String id;
    private String phenomenon;
    private String createTime;
    private String repairTime;
    private String isDelete;
    private String state;
    private String smallOwnerId;
    private String name;
    private String tel;
    private String address;
    private String brand;
    private String isPayment;
    private String evaluate;
    private String evaluateInfo;
    private String code;
    private String repairTypeId;
    private String repairTypeName;
    private String stateStr;

    private RepairTypeInfoBean repairTypeInfo;


    public RepairTypeInfoBean getRepairTypeInfo() {
        return repairTypeInfo;
    }

    public void setRepairTypeInfo(RepairTypeInfoBean repairTypeInfo) {
        this.repairTypeInfo = repairTypeInfo;
    }

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

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSmallOwnerId() {
        return smallOwnerId;
    }

    public void setSmallOwnerId(String smallOwnerId) {
        this.smallOwnerId = smallOwnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

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

    public String getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(String isPayment) {
        this.isPayment = isPayment;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getEvaluateInfo() {
        return evaluateInfo;
    }

    public void setEvaluateInfo(String evaluateInfo) {
        this.evaluateInfo = evaluateInfo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRepairTypeId() {
        return repairTypeId;
    }

    public void setRepairTypeId(String repairTypeId) {
        this.repairTypeId = repairTypeId;
    }

    public String getRepairTypeName() {
        return repairTypeName;
    }

    public void setRepairTypeName(String repairTypeName) {
        this.repairTypeName = repairTypeName;
    }

    public class RepairTypeInfoBean implements Serializable {


        /**
         * content : 曳引系统的主要功能是输出与传递动力，使电梯运行。 曳引系统主要由曳引机、曳引钢丝绳，导向轮，反绳轮组成
         * createTime : 2017-03-06 11:07:59
         * id : 4cc719db-123b-431d-b05d-c9b68fa9e2fd
         * name : 曳引系统
         */

        private String content;
        private String createTime;
        private String id;
        private String name;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
