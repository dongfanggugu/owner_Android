package com.honyum.owner.net;

import com.honyum.owner.data.RepairOrderInfo;
import com.honyum.owner.net.base.Response;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/3/1 0001
 */

public class MyRepairOrderResponse extends Response {

    private List<RepairOrderInfo> body;

    public List<RepairOrderInfo> getBody() {
        return body;
    }

    public void setBody(List<RepairOrderInfo> body) {
        this.body = body;
    }

    public static MyRepairOrderResponse getResult(String json) {
        return (MyRepairOrderResponse) parseFromJson(MyRepairOrderResponse.class, json);
    }
}
