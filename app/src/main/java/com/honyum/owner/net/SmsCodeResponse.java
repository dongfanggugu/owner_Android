package com.honyum.owner.net;

import com.honyum.owner.net.base.Response;
import com.honyum.owner.net.base.ResponseBody;

/**
 * Created by 李有鬼 on 2017/2/28 0028
 */

public class SmsCodeResponse extends Response {

    private SmsCodeResBody body;

    public SmsCodeResBody getBody() {
        return body;
    }

    public void setBody(SmsCodeResBody body) {
        this.body = body;
    }

    public class SmsCodeResBody extends ResponseBody {

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static SmsCodeResponse getResult(String json) {
        return (SmsCodeResponse) parseFromJson(SmsCodeResponse.class, json);
    }
}
