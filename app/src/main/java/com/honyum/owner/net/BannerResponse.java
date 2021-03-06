package com.honyum.owner.net;

import com.honyum.owner.data.BannerInfo;
import com.honyum.owner.net.base.Response;

import java.util.List;

/**
 * Created by 李有鬼 on 2017/1/17 0017
 */

public class BannerResponse extends Response {

    private List<BannerInfo> body;

    public List<BannerInfo> getBody() {
        return body;
    }

    public void setBody(List<BannerInfo> body) {
        this.body = body;
    }

    public static BannerResponse getResult(String json) {
        return (BannerResponse) parseFromJson(BannerResponse.class, json);
    }
}
