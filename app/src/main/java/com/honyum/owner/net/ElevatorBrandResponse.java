package com.honyum.owner.net;

import com.honyum.owner.data.ElevatorInfo;
import com.honyum.owner.net.base.Response;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/2/28 0028
 */

public class ElevatorBrandResponse extends Response {

    private List<ElevatorInfo> body;

    public List<ElevatorInfo> getBody() {
        return body;
    }

    public void setBody(List<ElevatorInfo> body) {
        this.body = body;
    }

    public static ElevatorBrandResponse getResult(String json) {
        return (ElevatorBrandResponse) parseFromJson(ElevatorBrandResponse.class, json);
    }
}
