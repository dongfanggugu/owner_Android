package com.honyum.owner.net;

import com.honyum.owner.data.MtTaskInfo;
import com.honyum.owner.net.base.Response;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/3/2 0002
 */

public class MtTaskResponse extends Response {

    private List<MtTaskInfo> body;

    public List<MtTaskInfo> getBody() {
        return body;
    }

    public void setBody(List<MtTaskInfo> body) {
        this.body = body;
    }

    public static MtTaskResponse getResult(String json) {
        return (MtTaskResponse) parseFromJson(MtTaskResponse.class, json);
    }
}
