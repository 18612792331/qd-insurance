package com.qding.api.call.app.qding.v1_3_1.struct.brick.response.data;

import com.qding.api.struct.ResponseData;

import java.util.UUID;

/**
 * Created by qd on 2015/11/13.
 */
public class CreateVerifyKeyResponseData  extends ResponseData {

    private static final long serialVersionUID = -2907063035395762158L;

    private String verifyKey = UUID.randomUUID().toString();

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
    }


    @Override
    public String toString() {
        return "CreateVerifyKeyResponseData [verifyKey=" + verifyKey
                + ", toString()=" + super.toString() + "]";
    }
}
