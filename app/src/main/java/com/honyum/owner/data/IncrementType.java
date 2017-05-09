package com.honyum.owner.data;

import com.honyum.owner.net.base.ResponseBody;

/**
 * Created by 李有鬼 on 2017/5/6 0006.
 */

public class IncrementType extends ResponseBody {


    /**
     * createTime : 2017-05-05 14:56:11
     * content :  24小时监控您的电梯运行状态
     * id : b77c4c58-a0fe-42ea-855d-db6ee8d68ed1
     * name : 智能小助手
     * price : 0.01
     * logo : http://123.57.10.16:8081/static/27f18342-92c9-499a-b208-1dca9d69b1e9.png
     */

    private String createTime;
    private String content;
    private String id;
    private String name;
    private double price;
    private String logo;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
