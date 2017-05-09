package com.honyum.owner.net;

import com.honyum.owner.data.MtOrderInfo;
import com.honyum.owner.net.base.Response;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/3/1 0001
 */

public class MyMtOrderResponse extends Response {

    private List<MtOrderInfo> body;

    public List<MtOrderInfo> getBody() {
        return body;
    }

    public void setBody(List<MtOrderInfo> body) {
        this.body = body;
    }

    public static MyMtOrderResponse getResult(String json) {
        return (MyMtOrderResponse) parseFromJson(MyMtOrderResponse.class, json);
    }
}
