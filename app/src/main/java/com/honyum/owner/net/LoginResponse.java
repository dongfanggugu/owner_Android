package com.honyum.owner.net;

import com.honyum.owner.data.LoginResBody;
import com.honyum.owner.net.base.Response;

/**
 * Created by 李有鬼 on 2017/1/8 0008
 */

public class LoginResponse extends Response {

    private LoginResBody body;

    public LoginResBody getBody() {
        return body;
    }

    public void setBody(LoginResBody body) {
        this.body = body;
    }

    public static LoginResponse getResult(String json) {
        return (LoginResponse) parseFromJson(LoginResponse.class, json);
    }
}
