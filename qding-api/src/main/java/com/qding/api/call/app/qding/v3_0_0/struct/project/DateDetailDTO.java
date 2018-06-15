package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/3/3.
 */
public class DateDetailDTO extends DateDTO implements Serializable {

    private static final long serialVersionUID = -7083077082446151841L;

    @ExplainAnnotation (explain = "当日活动列表")
    private List<ActivityCalendarDTO> curDayActivityList;

    @ExplainAnnotation (explain = "当日日历图片")
    private String calendarImg;

    @ExplainAnnotation (explain = "当日民俗节气描述")
    private String  dateDesc;

    @ExplainAnnotation (explain = "当日民俗节气标题")
    private String dateTitle;

    public String getDateTitle() {
        return dateTitle;
    }

    public void setDateTitle(String dateTitle) {
        this.dateTitle = dateTitle;
    }

    public List<ActivityCalendarDTO> getCurDayActivityList() {
        return curDayActivityList;
    }

    public void setCurDayActivityList(List<ActivityCalendarDTO> curDayActivityList) {
        this.curDayActivityList = curDayActivityList;
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
}
