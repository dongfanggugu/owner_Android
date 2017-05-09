package com.honyum.owner.net;

import com.honyum.owner.data.IncrementType;
import com.honyum.owner.net.base.Response;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/5/6 0006.
 */

public class GetIncrementTypeListResponse extends Response {

    private List<IncrementType> body;

    public List<IncrementType> getBody() {
        return body;
    }

    public void setBody(List<IncrementType> body) {
        this.body = body;
    }

    public static GetIncrementTypeListResponse getResult(String json) {
        return (GetIncrementTypeListResponse) parseFromJson(GetIncrementTypeListResponse.class, json);
    }
}
