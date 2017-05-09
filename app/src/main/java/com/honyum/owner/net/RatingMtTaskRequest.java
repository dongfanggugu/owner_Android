package com.honyum.owner.net;

import com.honyum.owner.net.base.RequestBean;

import java.io.Serializable;

/**
 * Created by 李有鬼 on 2017/1/11 0011
 */

public class RatingMtTaskRequest extends RequestBean {

    private RatingMtTaskReqBody body;

    public RatingMtTaskReqBody getBody() {
        return body;
    }

    public void setBody(RatingMtTaskReqBody body) {
        this.body = body;
    }

    public class RatingMtTaskReqBody implements Serializable {

        /**
         * maintOrderProcessId : 75b41df9-910d-411a-9b7e-29485047ab7c
         * evaluateContent :
         * evaluateResult : 5
         */

        private String maintOrderProcessId;
        private String evaluateContent;
        private int evaluateResult;

        public String getMaintOrderProcessId() {
            return maintOrderProcessId;
        }

        public void setMaintOrderProcessId(String maintOrderProcessId) {
            this.maintOrderProcessId = maintOrderProcessId;
        }

        public String getEvaluateContent() {
            return evaluateContent;
        }

        public void setEvaluateContent(String evaluateContent) {
            this.evaluateContent = evaluateContent;
        }

        public int getEvaluateResult() {
            return evaluateResult;
        }

        public void setEvaluateResult(int evaluateResult) {
            this.evaluateResult = evaluateResult;
        }
    }
}
