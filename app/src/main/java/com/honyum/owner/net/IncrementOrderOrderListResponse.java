package com.honyum.owner.net;

import com.honyum.owner.data.IncrementOrderOrderInfo;
import com.honyum.owner.net.base.Response;

import java.util.List;

/**
 * Created by LiYouGui on 2017/5/8.
 */

public class IncrementOrderOrderListResponse extends Response {

    private List<IncrementOrderOrderInfo> body;

    public List<IncrementOrderOrderInfo> getBody() {
        return body;
    }

    public void setBody(List<IncrementOrderOrderInfo> body) {
        this.body = body;
    }

    public static IncrementOrderOrderListResponse getResult(String json) {
        return (IncrementOrderOrderListResponse) parseFromJson(IncrementOrderOrderListResponse.class, json);
    }
}
