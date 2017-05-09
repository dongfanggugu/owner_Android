package com.honyum.owner.data;

import com.honyum.owner.net.base.ResponseBody;

/**
 * Created by 李有鬼 on 2017/2/28 0028
 */
public class ElevatorInfo extends ResponseBody {


    /**
     * description : 帝森克虏伯
     * id : 298c020c-4831-42cd-bae5-3716f18229cc
     * name : 帝森克虏伯
     * type : 电梯品牌
     * value : 帝森克虏伯
     */

    private String description;
    private String id;
    private String name;
    private String type;
    private String value;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
