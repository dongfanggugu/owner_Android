package com.honyum.owner.net;

import com.honyum.owner.data.AddIncrementResBody;
import com.honyum.owner.net.base.Response;

/**
 * Created by 李有鬼 on 2017/5/6 0006.
 */

public class AddIncrementResponse extends Response {

    private AddIncrementResBody body;

    public AddIncrementResBody getBody() {
        return body;
    }

    public void setBody(AddIncrementResBody body) {
        this.body = body;
    }

    public static AddIncrementResponse getResult(String json) {
        return (AddIncrementResponse) parseFromJson(AddIncrementResponse.class, json);
    }
}
