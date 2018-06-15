package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/28.
 */
public class CalendarBoard extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -4367493558361099871L;

    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;

    @ExplainAnnotation (explain = "日历背景图")
    private CalendarBackgroundDTO calendarBackground;

    @ExplainAnnotation (explain = "天气情况")
    private WeatherDTO  weatherInfo;

    @ExplainAnnotation (explain = "日期信息")
    private DateDTO  dateInfo;

    @ExplainAnnotation (explain = "当天活动信息")
    private List<ActivityCalendarDTO> activityList;

    @ExplainAnnotation (explain = "日历类型",desc = "1:普通日历，2：整合营销")
    private Integer calendarType;

    @ExplainAnnotation (explain = "日历字体提颜色")
    private String calendarFontColor;

    public Integer getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(Integer calendarType) {
        this.calendarType = calendarType;
    }

    public CalendarBackgroundDTO getCalendarBackground() {
        return calendarBackground;
    }

    public void setCalendarBackground(CalendarBackgroundDTO calendarBackground) {
        this.calendarBackground = calendarBackground;
    }

    public WeatherDTO getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherDTO weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public DateDTO getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(DateDTO dateInfo) {
        this.dateInfo = dateInfo;
    }

    public List<ActivityCalendarDTO> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityCalendarDTO> activityList) {
        this.activityList = activityList;
    }

    public String getCalendarFontColor() {
        return calendarFontColor;
    }

    public void setCalendarFontColor(String calendarFontColor) {
        this.calendarFontColor = calendarFontColor;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }
}
