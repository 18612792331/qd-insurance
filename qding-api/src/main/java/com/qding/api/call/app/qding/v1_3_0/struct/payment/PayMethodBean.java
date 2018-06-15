package com.qding.api.call.app.qding.v1_3_0.struct.payment;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by qd on 2016/5/25.
 */
public class PayMethodBean implements Serializable {

    private static final long serialVersionUID = 5906710026991840501L;

    @ExplainAnnotation(explain = "支付方式类型")
    private Integer type;

    @ExplainAnnotation(explain = "支付方式名称")
    private String name;

    @ExplainAnnotation(explain = "支付方式图标")
    private String icon;

    @ExplainAnnotation(explain = "描述")
    private String desc;

    @ExplainAnnotation(explain = "支付营销活动")
    private String activity;

     @ExplainAnnotation(explain = "是否是默认支付方式")
    private Integer defaultFlag;

    @ExplainAnnotation(explain = "组合支付中该支付方式应该支付金额")
    private String combinationShouldPay;

    @ExplainAnnotation(explain = "可用额度",desc = "目前为总可用金额，后期如果需要设置额度上限,即使用此字段")
    private String quotaAmount;


    public String getCombinationShouldPay() {
        return combinationShouldPay;
    }

    public void setCombinationShouldPay(String combinationShouldPay) {
        this.combinationShouldPay = combinationShouldPay;
    }

    public String getQuotaAmount() {
        return quotaAmount;
    }

    public void setQuotaAmount(String quotaAmount) {
        this.quotaAmount = quotaAmount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }


    public PayMethodBean() {
    }

    public PayMethodBean(Integer type, String name, String icon, String desc, String activity, Integer defaultFlag, String combinationShouldPay, String quotaAmount) {
        this.type = type;
        this.name = name;
        this.icon = icon;
        this.desc = desc;
        this.activity = activity;
        this.defaultFlag = defaultFlag;
        this.combinationShouldPay = combinationShouldPay;
        this.quotaAmount = quotaAmount;
    }
}
