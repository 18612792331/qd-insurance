package com.qding.api.call.app.qding.v3_3_0.struct.realestate;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Create by jinhaishan on 17/12/8
 **/
public class LogisticsInfo implements Serializable {
    private static final long serialVersionUID = -1514873125327361162L;
    /**
     * 时间
     */
    @ExplainAnnotation(explain = "时间")
    private Long time;
    /**
     * 内容
     */
    @ExplainAnnotation(explain = "内容")
    private String context;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "LogisticsInfoDto{" +
                "time=" + time +
                ", context='" + context + '\'' +
                '}';
    }
}
