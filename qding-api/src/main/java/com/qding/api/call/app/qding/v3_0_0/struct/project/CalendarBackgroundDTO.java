package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/5.
 */
public class CalendarBackgroundDTO implements Serializable {

    private static final long serialVersionUID = -7835650641233251366L;

    @ExplainAnnotation(explain = "日历大背景图")
    private String maxImgUrl;

    @ExplainAnnotation(explain = "活动时日期背景小图")
    private String minImgUrl;

    public String getMaxImgUrl() {
        return maxImgUrl;
    }

    public void setMaxImgUrl(String maxImgUrl) {
        this.maxImgUrl = maxImgUrl;
    }

    public String getMinImgUrl() {
        return minImgUrl;
    }

    public void setMinImgUrl(String minImgUrl) {
        this.minImgUrl = minImgUrl;
    }
}
