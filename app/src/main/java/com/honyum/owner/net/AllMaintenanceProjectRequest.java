package com.honyum.owner.net;

import com.honyum.owner.net.base.RequestBean;

/**
 * Created by 李有鬼 on 2017/1/8 0008
 */

public class AllMaintenanceProjectRequest extends RequestBean {

    private AllMaintenanceProjectReqBody body;

    public AllMaintenanceProjectReqBody getBody() {
        return body;
    }

    public void setBody(AllMaintenanceProjectReqBody body) {
        this.body = body;
    }

    public class AllMaintenanceProjectReqBody {

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
