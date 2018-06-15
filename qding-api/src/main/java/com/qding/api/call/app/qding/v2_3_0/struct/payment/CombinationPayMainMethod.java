package com.qding.api.call.app.qding.v2_3_0.struct.payment;

import com.qding.api.call.app.qding.v1_3_0.struct.payment.PayMethodBean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by qd on 2016/5/18.
 */
public class CombinationPayMainMethod extends PayMethodBean implements Serializable {

    private static final long serialVersionUID = -5862971547801410543L;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CombinationPayMainMethod() {
    }

    public CombinationPayMainMethod(String value) {
        this.value = value;
    }

    public CombinationPayMainMethod(Integer type, String name, String icon, String desc, String activity, Integer defaultFlag, String combinationShouldPay, String quotaAmount, String value) {
        super(type, name, icon, desc, activity, defaultFlag, combinationShouldPay, quotaAmount);
        this.value = value;
    }
}
