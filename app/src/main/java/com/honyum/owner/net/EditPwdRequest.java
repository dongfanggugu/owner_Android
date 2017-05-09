package com.honyum.owner.net;

import com.honyum.owner.net.base.RequestBean;

import java.io.Serializable;

/**
 * Created by 李有鬼 on 2017/1/13 0013
 */

public class EditPwdRequest extends RequestBean {

    private EditPwdReqBody body;

    public EditPwdReqBody getBody() {
        return body;
    }

    public void setBody(EditPwdReqBody body) {
        this.body = body;
    }

    public class EditPwdReqBody implements Serializable {

        private String oldPwd;

        private String newPwd;

        public String getOldPwd() {
            return oldPwd;
        }

        public void setOldPwd(String oldPwd) {
            this.oldPwd = oldPwd;
        }

        public String getNewPwd() {
            return newPwd;
        }

        public void setNewPwd(String newPwd) {
            this.newPwd = newPwd;
        }
    }
}
