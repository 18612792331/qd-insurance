package com.qding.api.call.app.qding.v2_3_0.struct.payment;

import java.io.Serializable;

/**
 * Created by qd on 2016/5/25.
 */
public class CombinationInfoForThd implements Serializable {

    private Integer type;

    private String shoudPay;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getShoudPay() {
        return shoudPay;
    }

    public void setShoudPay(String shoudPay) {
        this.shoudPay = shoudPay;
    }

    public CombinationInfoForThd() {
    }

    public CombinationInfoForThd(Integer type, String shoudPay) {
        this.type = type;
        this.shoudPay = shoudPay;
    }
}
