package com.honyum.owner.net.base;

/**
 * 请求报文公共head
 *
 * @author chang
 */
public class RequestHead {

    private String osType = "2"; // 移动端系统标记, 1:ios 2:android

    private String userId = ""; // 用户id

    private String deviceId = ""; // 设备标识

    private String accessToken = "";

    private String requestToken = "";

    private String version = "";

    private String type = "";


    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}