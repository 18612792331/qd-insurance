package com.qding.api.call.app.qding.v1_3_2.struct.user.response.data;

import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/9/17.
 */
public class CheckVerifyCodeIsResetResponseData  extends ResponseData {


    private static final long serialVersionUID = 4175688271340128766L;

    private Integer isRest;

    public Integer getIsRest() {
        return isRest;
    }

    public void setIsRest(Integer isRest) {
        this.isRest = isRest;
    }

    @Override
    public String toString() {
        return "CheckVerifyCodeIsResetResponseData [isRest="+isRest+",toString()=" + super.toString() + "]";
    }
}
