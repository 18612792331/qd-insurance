package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/2/28.
 */
@ExplainAnnotation (explain = "日历日期信息")
public class DateDTO implements Serializable {

    private static final long serialVersionUID = 4644685808728955447L;

    @ExplainAnnotation(explain = "阳历年")
    private String solarYear;

    @ExplainAnnotation(explain = "阳历月")
    private String solarMonth;

    @ExplainAnnotation(explain = "阳历日")
    private String solarDay;

    @ExplainAnnotation(explain = "农历年")
    private String lunarYear;

    @ExplainAnnotation(explain = "农历月")
    private String lunarMonth;

    @ExplainAnnotation(explain = "农历日")
    private String lunarDay;

    @ExplainAnnotation(explain = "节气")
    private String solarTerm;

    @ExplainAnnotation(explain = "干支")
    private String cyclicalm;

    @ExplainAnnotation(explain = "农历节日")
    private String lunarHoliday;

    @ExplainAnnotation(explain = "阳历节日")
    private String  holiday;

    @ExplainAnnotation(explain = "阳历完整日期")
    private String hDate;

    @ExplainAnnotation(explain = "星期几",desc = "(星期日为:1, 星期六为:7)")
    private Integer week;

    public DateDTO(String solarYear, String solarMonth, String solarDay, String lunarYear, String lunarMonth, String lunarDay, String solarTerm, String cyclicalm, String lunarHoliday, String holiday, String hDate,Integer week) {
        this.solarYear = solarYear;
        this.solarMonth = solarMonth;
        this.solarDay = solarDay;
        this.lunarYear = lunarYear;
        this.lunarMonth = lunarMonth;
        this.lunarDay = lunarDay;
        this.solarTerm = solarTerm;
        this.cyclicalm = cyclicalm;
        this.lunarHoliday = lunarHoliday;
        this.holiday = holiday;
        this.hDate = hDate;
        this.week = week;
    }

    public DateDTO() {
    }

    public String gethDate() {
        return hDate;
    }

    public void sethDate(String hDate) {
        this.hDate = hDate;
    }

    public String getSolarYear() {
        return solarYear;
    }

    public void setSolarYear(String solarYear) {
        this.solarYear = solarYear;
    }

    public String getSolarMonth() {
        return solarMonth;
    }

    public void setSolarMonth(String solarMonth) {
        this.solarMonth = solarMonth;
    }

    public String getSolarDay() {
        return solarDay;
    }

    public void setSolarDay(String solarDay) {
        this.solarDay = solarDay;
    }

    public String getLunarYear() {
        return lunarYear;
    }

    public void setLunarYear(String lunarYear) {
        this.lunarYear = lunarYear;
    }

    public String getLunarMonth() {
        return lunarMonth;
    }

    public void setLunarMonth(String lunarMonth) {
        this.lunarMonth = lunarMonth;
    }

    public String getLunarDay() {
        return lunarDay;
    }

    public void setLunarDay(String lunarDay) {
        this.lunarDay = lunarDay;
    }

    public String getSolarTerm() {
        return solarTerm;
    }

    public void setSolarTerm(String solarTerm) {
        this.solarTerm = solarTerm;
    }

    public String getCyclicalm() {
        return cyclicalm;
    }

    public void setCyclicalm(String cyclicalm) {
        this.cyclicalm = cyclicalm;
    }

    public String getLunarHoliday() {
        return lunarHoliday;
    }

    public void setLunarHoliday(String lunarHoliday) {
        this.lunarHoliday = lunarHoliday;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}
