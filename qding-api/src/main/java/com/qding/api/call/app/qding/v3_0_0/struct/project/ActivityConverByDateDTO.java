package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/6.
 */
public class ActivityConverByDateDTO extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 2700175512575146003L;

    private String url;

    private String activityName;

    private String  activityTime;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }
}
