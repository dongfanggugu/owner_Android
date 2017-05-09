package com.honyum.owner.net;

import com.honyum.owner.data.AddRepairReqBody;
import com.honyum.owner.net.base.RequestBean;

/**
 * Created by 李有鬼 on 2017/1/8 0008
 */

public class AddRepairRequest extends RequestBean {

    private AddRepairReqBody body;

    public AddRepairReqBody getBody() {
        return body;
    }

    public void setBody(AddRepairReqBody body) {
        this.body = body;
    }
}
