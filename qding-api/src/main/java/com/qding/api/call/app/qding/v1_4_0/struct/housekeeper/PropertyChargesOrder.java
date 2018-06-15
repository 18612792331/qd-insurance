package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2015/9/19.
 */
public class PropertyChargesOrder implements Serializable {


    private static final long serialVersionUID = -9113745014025060564L;

    @ExplainAnnotation (explain = "订单号")
    private String orderCode;

    @ExplainAnnotation (explain = "总金额")
    private String totalPrice;

    @ExplainAnnotation (explain = "缴费类型")
    private String type="WF";

    @ExplainAnnotation (explain = "费用所属的人的第三方custID，也就是要给谁代缴")
    private String feeOwnersCustid;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFeeOwnersCustid() {
        return feeOwnersCustid;
    }

    public void setFeeOwnersCustid(String feeOwnersCustid) {
        this.feeOwnersCustid = feeOwnersCustid;
    }
}
