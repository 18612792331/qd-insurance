package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/22.
 */
public class SumFee implements Serializable {

    private static final long serialVersionUID = -1459097408581025701L;

    private Float debts = Float.valueOf(0.0F);
    private Float prePay = Float.valueOf(0.0F);

    public SumFee() {
    }

    public Float getDebts() {
        return this.debts;
    }

    public void setDebts(Float debts) {
        this.debts = debts;
    }

    public Float getPrePay() {
        return this.prePay;
    }

    public void setPrePay(Float prePay) {
        this.prePay = prePay;
    }


}
