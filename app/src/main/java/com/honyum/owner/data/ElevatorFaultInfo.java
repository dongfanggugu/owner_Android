package com.honyum.owner.data;

import com.honyum.owner.net.base.ResponseBody;

/**
 * Created by 李有鬼 on 2017/3/1 0001
 */
public class ElevatorFaultInfo extends ResponseBody {

    /**
     * id : 维修类型ID
     * name : 维修类型名称
     * content : 维修类型详细内容
     * createTime : 创建时间
     */

    private String id;
    private String name;
    private String content;
    private String createTime;

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
}
