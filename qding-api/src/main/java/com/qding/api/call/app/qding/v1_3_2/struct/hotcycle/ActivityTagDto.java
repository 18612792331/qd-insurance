package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle;

import java.io.Serializable;

/**
 * Created by JIAWENZHENG on 2015/7/27.
 */
public class ActivityTagDto implements Serializable {

    private static final long serialVersionUID = 5636512254482251902L;
    /**
     * 标签ID
     */
    private String tagId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 标签所属活动ID
     */
    private String activityId;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}
