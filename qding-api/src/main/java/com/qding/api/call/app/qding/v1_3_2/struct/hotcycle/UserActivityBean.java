package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/27.
 */
public class UserActivityBean  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1736004777161769996L;

	/**
     * 活动ID
     */
    private String activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动缩略图
     */
    private String tagThumbnails;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getTagThumbnails() {
        return tagThumbnails;
    }

    public void setTagThumbnails(String tagThumbnails) {
        this.tagThumbnails = tagThumbnails;
    }
}
