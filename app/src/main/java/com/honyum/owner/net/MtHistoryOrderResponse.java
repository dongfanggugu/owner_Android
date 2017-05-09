package com.honyum.owner.net;

import com.honyum.owner.data.MtHistoryOrderInfo;
import com.honyum.owner.net.base.Response;

import java.util.List;

/**
 * Created by LiYouGui on 2017/5/8.
 */

public class MtHistoryOrderResponse extends Response {

    private List<MtHistoryOrderInfo> body;

    public List<MtHistoryOrderInfo> getBody() {
        return body;
    }

    public void setBody(List<MtHistoryOrderInfo> body) {
        this.body = body;
    }

    public static MtHistoryOrderResponse getResult(String json) {
        return (MtHistoryOrderResponse) parseFromJson(MtHistoryOrderResponse.class, json);
    }
}
