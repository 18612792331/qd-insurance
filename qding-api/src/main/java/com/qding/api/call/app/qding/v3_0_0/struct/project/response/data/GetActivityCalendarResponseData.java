package com.qding.api.call.app.qding.v3_0_0.struct.project.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.project.ActivityCalendarDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.project.DateDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.project.DateDetailDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.project.WeatherDTO;
import com.qding.api.struct.ResponseData;
import java.util.List;

/**
 * Created by qd on 2017/2/23.
 */
public class GetActivityCalendarResponseData extends ResponseData {

    private static final long serialVersionUID = -3699155067065165787L;

   /* @ExplainAnnotation (explain = "日历活动列表")
    private List<ActivityCalendarDTO> calendarActivityList;*/

    @ExplainAnnotation (explain = "天气列表")
    private List<WeatherDTO> weatherList;

    @ExplainAnnotation (explain = "日历及活动列表信息")
    private List<DateDetailDTO>  dateList;

    @ExplainAnnotation (explain = "当天日历信息")
    private DateDetailDTO curDate;

    @ExplainAnnotation (explain = "日历标题")
    private String title;


/*    @ExplainAnnotation (explain = "指定月日历信息")
    private List<DateDTO> monthDateList;*/

    public List<WeatherDTO> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<WeatherDTO> weatherList) {
        this.weatherList = weatherList;
    }

    public List<DateDetailDTO> getDateList() {
        return dateList;
    }

    public void setDateList(List<DateDetailDTO> dateList) {
        this.dateList = dateList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DateDetailDTO getCurDate() {
        return curDate;
    }

    public void setCurDate(DateDetailDTO curDate) {
        this.curDate = curDate;
    }
}
