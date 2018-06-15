package com.qding.api.call.app.qding.v1_3_0.struct.payment;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/7/17.
 */
public class OneNetArgument  implements Serializable{

    private static final long serialVersionUID = -8833155050232079281L;

    @ExplainAnnotation (explain = "支付参数")
    private String signData;

    @ExplainAnnotation (explain = "支付URL")
    private String payUrl;

    public String getSignData() {
        return signData;
    }

    public void setSignData(String signData) {
        this.signData = signData;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }
}
