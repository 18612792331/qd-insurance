package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle;

import java.io.Serializable;

/**
 * Created by jiawenzheng on 2015/7/23.
 */
public class ActivityDetail implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1749674885590894028L;

    /**
     * 活动ID
     */
    private String activityId;

    /**
     * 活动名称
     */
    private String activityName;


    /**
     * 系统标签名称
     */
    private String activityTagName;

    /**
     * 活动图片
     */
    private String activityTagImage;

    /**
     * 活动简介
     */
    private String activityTagIntro;

    /**
     * 活动说明
     */
    private  String activityTagExplain;
    
    /**
     * 活动状态
     */
    private Integer activityStatus;


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

    public String getActivityTagName() {
        return activityTagName;
    }

    public void setActivityTagName(String activityTagName) {
        this.activityTagName = activityTagName;
    }

    public String getActivityTagImage() {
        return activityTagImage;
    }

    public void setActivityTagImage(String activityTagImage) {
        this.activityTagImage = activityTagImage;
    }

    public String getActivityTagIntro() {
        return activityTagIntro;
    }

    public void setActivityTagIntro(String activityTagIntro) {
        this.activityTagIntro = activityTagIntro;
    }

    public String getActivityTagExplain() {
        return activityTagExplain;
    }

    public void setActivityTagExplain(String activityTagExplain) {
        this.activityTagExplain = activityTagExplain;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }
}
