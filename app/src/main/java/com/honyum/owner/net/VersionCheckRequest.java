package com.honyum.owner.net;


import com.honyum.owner.net.base.RequestBean;
import com.honyum.owner.net.base.RequestHead;

public class VersionCheckRequest extends RequestBean {

    private RequestHead head;

    public RequestHead getHead() {
        return head;
    }

    public void setHead(RequestHead head) {
        this.head = head;
    }
}
