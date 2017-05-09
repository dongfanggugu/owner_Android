package com.honyum.owner.net;

import com.honyum.owner.net.base.RequestBean;
import com.honyum.owner.net.base.RequestBody;

/**
 * Created by 李有鬼 on 2017/5/6 0006.
 */

public class AddGuaranteeRequest extends RequestBean {

    private AddGuaranteeReqBody body;

    public AddGuaranteeReqBody getBody() {
        return body;
    }

    public void setBody(AddGuaranteeReqBody body) {
        this.body = body;
    }

    public class AddGuaranteeReqBody extends RequestBody {

        private String name;

        private String tel;

        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
