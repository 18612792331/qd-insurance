package com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.response;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;


/**
 * Created by xuxiaoxing on 2018/5/3.
 */
public class CouponDTO implements Serializable {

    @ExplainAnnotation(explain = "券号")
    private String code;

    @ExplainAnnotation (explain = "金额")
    private Integer price;

    @ExplainAnnotation (explain = "有效起始日期")
    private Long startTime;

    @ExplainAnnotation (explain = "有效截止日期")
    private Long endTime;

    @ExplainAnnotation (explain = "券标题")
    private String note;

    @ExplainAnnotation (explain = "全描述")
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
