package com.honyum.owner.net;


import com.honyum.owner.net.base.Response;
import com.honyum.owner.net.base.ResponseHead;

public class VersionCheckResponse extends Response {

    private ResponseHead head;

    private VersionCheckRspBody body;

    public VersionCheckRspBody getBody() {
        return body;
    }

    public void setBody(VersionCheckRspBody body) {
        this.body = body;
    }

    @Override
    public ResponseHead getHead() {
        return head;
    }

    @Override
    public void setHead(ResponseHead head) {
        this.head = head;
    }

    public class VersionCheckRspBody {

        private int appVersion;

        private String isForce = "0";

        private String url = "";

        private String description = "";

        public int getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(int appVersion) {
            this.appVersion = appVersion;
        }

        public String getIsForce() {
            return isForce;
        }

        public void setIsForce(String isForce) {
            this.isForce = isForce;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    /**
     * 根据json返回对象
     *
     * @param json
     * @return
     */
    public static VersionCheckResponse getVersionCheckResponse(String json) {
        return (VersionCheckResponse) parseFromJson(VersionCheckResponse.class, json);
    }
}
