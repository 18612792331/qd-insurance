package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle;

import java.io.Serializable;

/**
 * Created by jiawenzheng on 2015/7/23.
 */
public class ActivityBean extends  ActivityBrief implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1749674885590894028L;



    /**
     * 销售平台
     */
    private String platformType;

    /**
     * 活动标签ID
     */
    private String activityTagId;

    /**
     * 系统标签ID
     */
    private String tagId;



    /**
     * 活动图片
     */
    private String tagImage;

    /**
     * 活动简介
     */
    private String tagIntro;

    /**
     * 活动说明
     */
    private  String tagExplain;

    /**
     * 关联名称
     */
    private String relevancyName;

    /**
     * 活动关联配置
     */
    private String relevancyProperty;


    /**
     * 所属群组
     */
    private String businessIds;


    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getActivityTagId() {
        return activityTagId;
    }

    public void setActivityTagId(String activityTagId) {
        this.activityTagId = activityTagId;
    }


    public String getTagImage() {
        return tagImage;
    }

    public void setTagImage(String tagImage) {
        this.tagImage = tagImage;
    }

    public String getTagIntro() {
        return tagIntro;
    }

    public void setTagIntro(String tagIntro) {
        this.tagIntro = tagIntro;
    }

    public String getTagExplain() {
        return tagExplain;
    }

    public void setTagExplain(String tagExplain) {
        this.tagExplain = tagExplain;
    }

    public String getRelevancyName() {
        return relevancyName;
    }

    public void setRelevancyName(String relevancyName) {
        this.relevancyName = relevancyName;
    }

    public String getRelevancyProperty() {
        return relevancyProperty;
    }

    public void setRelevancyProperty(String relevancyProperty) {
        this.relevancyProperty = relevancyProperty;
    }

    public String getBusinessIds() {
        return businessIds;
    }

    public void setBusinessIds(String businessIds) {
        this.businessIds = businessIds;
    }


    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }


}
