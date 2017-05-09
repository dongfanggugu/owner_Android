package com.honyum.owner.net;

import com.honyum.owner.data.AddIncrementReqBody;
import com.honyum.owner.net.base.RequestBean;

/**
 * Created by 李有鬼 on 2017/5/6 0006.
 */

public class AddIncrementRequest extends RequestBean{

    private AddIncrementReqBody body;

    public AddIncrementReqBody getBody() {
        return body;
    }

    public void setBody(AddIncrementReqBody body) {
        this.body = body;
    }
}
