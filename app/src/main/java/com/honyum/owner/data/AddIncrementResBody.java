package com.honyum.owner.data;

import com.honyum.owner.net.base.ResponseBody;

/**
 * Created by 李有鬼 on 2017/5/6 0006.
 */

public class AddIncrementResBody extends ResponseBody {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
