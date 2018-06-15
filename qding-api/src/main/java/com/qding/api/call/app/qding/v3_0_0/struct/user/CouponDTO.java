package com.qding.api.call.app.qding.v3_0_0.struct.user;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/3.
 */
public class CouponDTO implements Serializable {

    private static final long serialVersionUID = 1337923211264668715L;

    @ExplainAnnotation (explain = "金额")
    private String amounts;

    @ExplainAnnotation (explain = "规则描述")
    private String rule;

    @ExplainAnnotation (explain = "有效起始时间")
    private Long validStartTime;

    @ExplainAnnotation (explain = "有效截止时间")
    private Long validEndTime;

    @ExplainAnnotation (explain = "领取状态",desc = "1:未领取,0:已领取，2:已过期")
    private Integer status;

    @ExplainAnnotation (explain = "礼包简介")
    private String desc;

    @ExplainAnnotation (explain = "礼包领取说明")
    private String explain;

    @ExplainAnnotation (explain = "领取生效日")
    private Integer receiveAfter;

    @ExplainAnnotation (explain = "领取生效天数")
    private Integer receivePeriod;

    public CouponDTO(String amounts, String rule, Long validStartTime, Long validEndTime, Integer status) {
        this.amounts = amounts;
        this.rule = rule;
        this.validStartTime = validStartTime;
        this.validEndTime = validEndTime;
        this.status = status;
    }

    public CouponDTO(){

    }

    public String getAmounts() {
        return amounts;
    }

    public void setAmounts(String amounts) {
        this.amounts = amounts;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Long getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(Long validStartTime) {
        this.validStartTime = validStartTime;
    }

    public Long getValidEndTime() {
        return validEndTime;
    }

    public void setValidEndTime(Long validEndTime) {
        this.validEndTime = validEndTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Integer getReceiveAfter() {
        return receiveAfter;
    }

    public void setReceiveAfter(Integer receiveAfter) {
        this.receiveAfter = receiveAfter;
    }

    public Integer getReceivePeriod() {
        return receivePeriod;
    }

    public void setReceivePeriod(Integer receivePeriod) {
        this.receivePeriod = receivePeriod;
    }
}
