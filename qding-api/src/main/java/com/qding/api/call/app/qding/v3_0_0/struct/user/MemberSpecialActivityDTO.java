package com.qding.api.call.app.qding.v3_0_0.struct.user;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/7/12.
 */
public class MemberSpecialActivityDTO extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -2853421486075570814L;

    @ExplainAnnotation(explain = "活动图片")
    private String activityImg;

    @ExplainAnnotation(explain = "活动标题")
    private String title;

    @ExplainAnnotation(explain = "是否小红点提醒",desc = "1:提醒,0:不提醒")
    private Integer isNew;

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }
}
