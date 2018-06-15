package com.qding.insurance.rpc.response;

import com.qding.framework.common.api.struct.response.BaseResponse;

public class IsInsuranceProductResponse extends BaseResponse {

    private static final long serialVersionUID = -5394543917075721464L;

    /**
     * 是否在保商品
     */
    private boolean isInsuranceProduct;

    /**
     * 不在保原因
     */
    private String message;

    public boolean isInsuranceProduct() {
        return isInsuranceProduct;
    }

    public void setInsuranceProduct(boolean isInsuranceProduct) {
        this.isInsuranceProduct = isInsuranceProduct;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
