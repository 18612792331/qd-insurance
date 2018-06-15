package com.qding.api.call.app.qding.v3_0_0.struct.task;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/7/12.
 */
public class EntranceGuardActivity extends SkipUrl implements Serializable{

    private static final long serialVersionUID = 3415226147324711747L;

    @ExplainAnnotation(explain = "活动图片")
    private String imgUrl;

    @ExplainAnnotation(explain = "活动ID")
    private String activityId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
