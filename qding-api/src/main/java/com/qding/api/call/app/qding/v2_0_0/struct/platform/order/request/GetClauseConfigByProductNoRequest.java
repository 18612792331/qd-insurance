package com.qding.api.call.app.qding.v2_0_0.struct.platform.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/8/22.
 */
@Validate
public class GetClauseConfigByProductNoRequest extends BaseRequest {

    private static final long serialVersionUID = -8967057948471050272L;

    @ExplainAnnotation (explain = "业态编号")
    @NotNullValidate
    private String productNo;

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        return "GetClauseConfigByProductNoRequest{" +
                "productNo='" + productNo + '\'' +
                '}';
    }
}
