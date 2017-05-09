package com.honyum.owner.net;

import com.honyum.owner.net.base.Response;
import com.honyum.owner.net.base.ResponseBody;

/**
 * Created by LiYouGui on 2017/5/8.
 */

public class PayUrlResponse extends Response {

    private PayUrlResBody body;

    public PayUrlResBody getBody() {
        return body;
    }

    public void setBody(PayUrlResBody body) {
        this.body = body;
    }

    public static class PayUrlResBody extends ResponseBody {

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static PayUrlResBody getResult(String json) {
        return ((PayUrlResponse) parseFromJson(PayUrlResponse.class, json)).getBody();
    }
}
