package com.honyum.owner.data;

import com.honyum.owner.net.base.ResponseBody;

/**
 * Created by 李有鬼 on 2017/2/28 0028
 */
public class MtTypeInfo extends ResponseBody {


    /**
     * content : 8000元/年
     * createTime : 2017-02-24 10:50:36
     * id : ad93ae56-8440-4b03-88f5-1fc22399df95
     * name : 全包
     * price : 8000
     */

    private String content;
    private String createTime;
    private String id;
    private String name;
    private double price;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
}
