package com.honyum.owner.net;

import com.honyum.owner.data.IncrementOrderInfo;
import com.honyum.owner.net.base.Response;

import java.util.List;

/**
 * Created by LiYouGui on 2017/5/8.
 */

public class IncrementOrderListResponse extends Response {

    private List<IncrementOrderInfo> body;

    public List<IncrementOrderInfo> getBody() {
        return body;
    }

    public void setBody(List<IncrementOrderInfo> body) {
        this.body = body;
    }

    public static IncrementOrderListResponse getResult(String json) {
        return (IncrementOrderListResponse) parseFromJson(IncrementOrderListResponse.class, json);
    }
}
