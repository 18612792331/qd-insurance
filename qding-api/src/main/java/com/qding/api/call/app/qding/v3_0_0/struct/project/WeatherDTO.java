package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/2/28.
 */
@ExplainAnnotation (explain = "天气情况")
public class WeatherDTO implements Serializable {

    private static final long serialVersionUID = 3346721906771266151L;

    @ExplainAnnotation (explain = "空气质量指数")
    private String aqi;

    @ExplainAnnotation (explain = "空气质量指数")
    private String quality;

    @ExplainAnnotation (explain = "pm2.5指数")
    private String pm25;

    @ExplainAnnotation (explain = "温度")
    private String temperature;

    @ExplainAnnotation (explain = "天气情况")
    private String weather;

    @ExplainAnnotation (explain = "风力风向")
    private String wind;

    @ExplainAnnotation (explain = "限行信息")
    private String limitLine;

    @ExplainAnnotation (explain = "日期")
    private long timestamp;

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getLimitLine() {
        return limitLine;
    }

    public void setLimitLine(String limitLine) {
        this.limitLine = limitLine;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
