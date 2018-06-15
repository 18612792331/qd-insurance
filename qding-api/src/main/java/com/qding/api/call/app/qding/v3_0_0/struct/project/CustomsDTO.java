package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/10.
 */
public class CustomsDTO implements Serializable {

    @ExplainAnnotation(explain = "展示时间")
    private String showTime;

    @ExplainAnnotation(explain = "当日日历图片")
    private String calendarImg;

    @ExplainAnnotation (explain = "当日民俗节气描述")
    private String  dateDesc;

    @ExplainAnnotation (explain = "当日民俗节气标题")
    private String dateTitle;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getCalendarImg() {
        return calendarImg;
    }

    public void setCalendarImg(String calendarImg) {
        this.calendarImg = calendarImg;
    }

    public String getDateDesc() {
        return dateDesc;
    }

    public void setDateDesc(String dateDesc) {
        this.dateDesc = dateDesc;
    }

    public String getDateTitle() {
        return dateTitle;
    }

    public void setDateTitle(String dateTitle) {
        this.dateTitle = dateTitle;
    }
}
