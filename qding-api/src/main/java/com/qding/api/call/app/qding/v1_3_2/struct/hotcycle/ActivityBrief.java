package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/29.
 */
public class ActivityBrief implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 7014471220386874654L;

	/**
     * 活动ID
     */
    private String activityId;

    /**
     * 活动缩略图
     */
    private String activityTagThumbnails;

    /**
     * 活动状态
     */
    private String activityStatus;

    /**
     * 活动名称
     */
    private String activityName;


    /**
     * 系统标签名称
     */
    private String activityTagName;
    
    
    /**
     * 活动简介
     */
    private String activityTagIntro;
    
    

    public String getActivityTagIntro() {
		return activityTagIntro;
	}

	public void setActivityTagIntro(String activityTagIntro) {
		this.activityTagIntro = activityTagIntro;
	}

	public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }


    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

	public String getActivityTagThumbnails() {
		return activityTagThumbnails;
	}

	public void setActivityTagThumbnails(String activityTagThumbnails) {
		this.activityTagThumbnails = activityTagThumbnails;
	}

	public String getActivityTagName() {
		return activityTagName;
	}

	public void setActivityTagName(String activityTagName) {
		this.activityTagName = activityTagName;
	}


}
