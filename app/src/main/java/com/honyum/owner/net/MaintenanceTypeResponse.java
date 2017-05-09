package com.honyum.owner.net;

import com.honyum.owner.data.MtTypeInfo;
import com.honyum.owner.net.base.Response;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/2/28 0028
 */

public class MaintenanceTypeResponse extends Response {

    private List<MtTypeInfo> body;

    public List<MtTypeInfo> getBody() {
        return body;
    }

    public void setBody(List<MtTypeInfo> body) {
        this.body = body;
    }

    public static MaintenanceTypeResponse getResult(String json) {
        return (MaintenanceTypeResponse) parseFromJson(MaintenanceTypeResponse.class, json);
    }
}
