package com.qding.api.call.app.qding.v3_0_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/3/3.
 */
@Validate
public class GetGiftDetailRequest extends BaseRequest {

    private static final long serialVersionUID = 1021059837406513213L;

    @ExplainAnnotation(explain = "礼包ID")
    @NotNullValidate
    private String giftId;

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }


    @Override
    public String toString() {
        return "GetGiftDetailRequest{" +
                "giftId='" + giftId + '\'' +
                '}';
    }
}
