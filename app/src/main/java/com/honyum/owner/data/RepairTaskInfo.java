package com.honyum.owner.data;

import java.io.Serializable;

/**
 * Created by 李有鬼 on 2017/1/9 0009
 */
public class RepairTaskInfo implements Serializable {

    /**
     * id : 任务单ID
     * repairOrderId : 订单ID
     * workerId : 维修工ID
     * workerName : 维修工姓名
     * workerTel : 维修工电话
     * startTime : 出发时间
     * arriveTime : 到达时间
     * createTime : 指派时间
     * finishResult : 维修结果
     * state : 状态 1待出发 2已出发 3工作中 5检修完成 6维修完成
     * code : 订单编号
     * phenomenon : 报修现象
     * repairTypeName : 故障类型名称
     * name : 业主姓名
     * tel : 业主电话
     * address : 住址
     * brand : 电梯品牌
     */

    private String id;
    private String repairOrderId;
    private String workerId;
    private String workerName;
    private String workerTel;
    private String startTime;
    private String arriveTime;
    private String createTime;
    private String finishResult;
    private String state;
    private String code;
    private String phenomenon;
    private String repairTypeName;
    private String name;
    private String tel;
    private String address;
    private String brand;
    private String stateStr;

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

    public String getRepairOrderId() {
        return repairOrderId;
    }

    public void setRepairOrderId(String repairOrderId) {
        this.repairOrderId = repairOrderId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerTel() {
        return workerTel;
    }

    public void setWorkerTel(String workerTel) {
        this.workerTel = workerTel;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishResult() {
        return finishResult;
    }

    public void setFinishResult(String finishResult) {
        this.finishResult = finishResult;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    public String getRepairTypeName() {
        return repairTypeName;
    }

    public void setRepairTypeName(String repairTypeName) {
        this.repairTypeName = repairTypeName;
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
}
