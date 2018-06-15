package com.qding.api.call.app.qding.v1_3_1.struct.brick.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/11/13.
 */

@Validate
public class ImgVerifyCodeRequest  extends BaseRequest {

    private static final long serialVersionUID = 8145788750095696430L;

    /**
     *（唯一标识）
     */
    @NotNullValidate
    private  String verifyKey;

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
    }

    @Override
    public String toString() {
        return "ImgVerifyCodeRequest [verifyKey="+verifyKey+"," +
                "super.toString() ]";
    }
}
