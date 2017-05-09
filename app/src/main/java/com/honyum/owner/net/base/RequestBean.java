package com.honyum.owner.net.base;


import com.honyum.owner.data.Atom;

public class RequestBean extends Atom {

    private RequestHead head;

    public RequestHead getHead() {
        return head;
    }

    public void setHead(RequestHead head) {
        this.head = head;
    }

    public static RequestBean getRequestBean(String json) {
        return (RequestBean) parseFromJson(RequestBean.class, json);
    }
}