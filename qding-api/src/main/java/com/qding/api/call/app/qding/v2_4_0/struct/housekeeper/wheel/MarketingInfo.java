package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.wheel;

public class MarketingInfo {

    private Integer id;

    private String name;

    private String startTime;

    private String startTimeFormat;

    private String endTime;

    private String endTimeFormat;

    private String wheelId;
    
    private Integer mustLongfor;
    
    
    public Integer getMustLongfor() {
        return mustLongfor;
    }

    
    public void setMustLongfor(Integer mustLongfor) {
        this.mustLongfor = mustLongfor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTimeFormat() {
        return startTimeFormat;
    }

    public void setStartTimeFormat(String startTimeFormat) {
        this.startTimeFormat = startTimeFormat;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeFormat() {
        return endTimeFormat;
    }

    public void setEndTimeFormat(String endTimeFormat) {
        this.endTimeFormat = endTimeFormat;
    }

    public String getWheelId() {
        return wheelId;
    }

    public void setWheelId(String wheelId) {
        this.wheelId = wheelId;
    }

}
