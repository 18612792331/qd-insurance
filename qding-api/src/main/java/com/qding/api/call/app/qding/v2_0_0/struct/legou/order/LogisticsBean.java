package com.qding.api.call.app.qding.v2_0_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/1/7.
 */
public class LogisticsBean implements Serializable {

    private static final long serialVersionUID = -9136256820980170420L;

    @ExplainAnnotation(explain = "地址描述")
    private String orderStatusContent;

    @ExplainAnnotation(explain = "记录时间")
    private Long recordTime;

    public String getOrderStatusContent() {
        return orderStatusContent;
    }

    public void setOrderStatusContent(String orderStatusContent) {
        this.orderStatusContent = orderStatusContent;
    }

    public Long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Long recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public String toString() {
        return "LogisticsBean{" +
                "orderStatusContent='" + orderStatusContent + '\'' +
                ", recordTime=" + recordTime +
                '}';
    }
}
