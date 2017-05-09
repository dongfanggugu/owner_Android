package com.honyum.owner.net;

import com.honyum.owner.net.base.RequestBean;

import java.io.Serializable;

/**
 * Created by 李有鬼 on 2017/1/11 0011
 */

public class RatingRepairRequest extends RequestBean {

    private RatingRepairReqBody body;

    public RatingRepairReqBody getBody() {
        return body;
    }

    public void setBody(RatingRepairReqBody body) {
        this.body = body;
    }

    public class RatingRepairReqBody implements Serializable {

        /**
         * repairOrderId : 99804729-ee08-43d0-a92f-4e37a37b1138
         * evaluate : 5
         * evaluateInfo : 1
         */

        private String repairOrderId;
        private String evaluate;
        private String evaluateInfo;

        public String getRepairOrderId() {
            return repairOrderId;
        }

        public void setRepairOrderId(String repairOrderId) {
            this.repairOrderId = repairOrderId;
        }

        public String getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(String evaluate) {
            this.evaluate = evaluate;
        }

        public String getEvaluateInfo() {
            return evaluateInfo;
        }

        public void setEvaluateInfo(String evaluateInfo) {
            this.evaluateInfo = evaluateInfo;
        }
    }
}
