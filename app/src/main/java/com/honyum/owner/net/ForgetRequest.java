package com.honyum.owner.net;

import com.honyum.owner.net.base.RequestBean;
import com.honyum.owner.net.base.RequestBody;

/**
 * Created by changhaozhang on 2017/5/17.
 */

public class ForgetRequest extends RequestBean {

    private ForgetReqBody body;

    public ForgetReqBody getBody() {
        return body;
    }

    public void setBody(ForgetReqBody body) {
        this.body = body;
    }

    public static class ForgetReqBody extends RequestBody {

        private String tel;

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
