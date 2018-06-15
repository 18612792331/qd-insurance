package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/19.
 */
@Validate
public class AlipayServeWindowRequest  extends BaseRequest {


    private static final long serialVersionUID = -4205250884093937228L;

    @NotNullValidate
    private String subBizType;

    public String getSubBizType() {
        return subBizType;
    }

    public void setSubBizType(String subBizType) {
        this.subBizType = subBizType;
    }

    @Override
    public String toString() {
        return "AlipayServeWindowRequest [subBizType=" + subBizType +", toString()=" + super.toString() + "]";
    }
}
