package com.qding.api.call.app.qding.v3_1_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;
/**
 * Created by qd on 2017/5/25.
 */
public class GetOrderPayStatusResponseData extends ResponseData {


    private static final long serialVersionUID = -7653406088049481999L;

    @ExplainAnnotation (explain = "支付状态",desc = "1:已支付，0：未支付")
    private Integer payStatus;

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public String toString() {
        return "GetOrderPayStatusResponseData{" +
                "payStatus=" + payStatus +
                '}';
    }
}
