package com.honyum.owner.net;

import com.honyum.owner.data.ElevatorFaultInfo;
import com.honyum.owner.net.base.Response;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/3/1 0001
 */

public class ElevatorFaultResponse extends Response {

    private List<ElevatorFaultInfo> body;

    public List<ElevatorFaultInfo> getBody() {
        return body;
    }

    public void setBody(List<ElevatorFaultInfo> body) {
        this.body = body;
    }

    public static ElevatorFaultResponse getResult(String json) {
        return (ElevatorFaultResponse) parseFromJson(ElevatorFaultResponse.class, json);
    }
}
