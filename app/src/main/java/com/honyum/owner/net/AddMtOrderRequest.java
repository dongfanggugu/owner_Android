package com.honyum.owner.net;

import com.honyum.owner.data.AddMtOrderReqBody;
import com.honyum.owner.net.base.RequestBean;

/**
 * Created by 李有鬼 on 2017/2/28 0028
 */

public class AddMtOrderRequest extends RequestBean {

    private AddMtOrderReqBody body;

    public AddMtOrderReqBody getBody() {
        return body;
    }

    public void setBody(AddMtOrderReqBody body) {
        this.body = body;
    }
}
