package com.honyum.owner.net;

import com.honyum.owner.data.MtOrderOrderInfo;
import com.honyum.owner.net.base.Response;

import java.util.List;

/**
 * Created by LiYouGui on 2017/5/8.
 */

public class MtOrderOrderResponse extends Response {

    private List<MtOrderOrderInfo> body;

    public List<MtOrderOrderInfo> getBody() {
        return body;
    }

    public void setBody(List<MtOrderOrderInfo> body) {
        this.body = body;
    }

    public static MtOrderOrderResponse getResult(String json) {
        return (MtOrderOrderResponse) parseFromJson(MtOrderOrderResponse.class, json);
    }
}
