package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/28.
 */
@ExplainAnnotation(explain = "日历信息")
public class ActivityCalendarBean implements Serializable {

    private static final long serialVersionUID = -8026844320407980003L;

    @ExplainAnnotation(explain = "近期天气情况")
    private List<WeatherDTO>  weatherList;

    @ExplainAnnotation(explain = "活动日历")
    private List<ActivityCalendarDTO> activityList;


    public List<WeatherDTO> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<WeatherDTO> weatherList) {
        this.weatherList = weatherList;
    }

    public List<ActivityCalendarDTO> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityCalendarDTO> activityList) {
        this.activityList = activityList;
    }
}
