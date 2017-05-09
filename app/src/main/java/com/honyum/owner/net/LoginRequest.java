package com.honyum.owner.net;

import com.honyum.owner.net.base.RequestBean;

import java.io.Serializable;

/**
 * Created by 李有鬼 on 2017/1/8 0008
 */

public class LoginRequest extends RequestBean {

    private LoginReqBody body;

    public LoginReqBody getBody() {
        return body;
    }

    public void setBody(LoginReqBody body) {
        this.body = body;
    }

    public class LoginReqBody implements Serializable {

        private String tel;

        private String password;

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
