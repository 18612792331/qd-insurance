package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/2/28.
 */
public class ProjectLifeDTO extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -3038064148420124234L;

    @ExplainAnnotation (explain = "帖子ID")
    private String topicId;

    @ExplainAnnotation (explain = "推荐帖子标题")
    private String topicTitle;

    @ExplainAnnotation (explain = "推荐帖子图片")
    private String topicImg;

    @ExplainAnnotation (explain = "帖子时间")
    private Long createTime;

    @ExplainAnnotation (explain = "标签")
    private String  interestTag;

    @ExplainAnnotation (explain = "帖子类型")
    private Integer topicType;

    @ExplainAnnotation (explain = "帖子类型名称")
    private String topicTypeName;

    @ExplainAnnotation (explain = "是否显示hot图标", desc = "true:显示，false:不显示")
    private boolean hot;

    @ExplainAnnotation (explain = "活动开始时间")
    private Long activityStartTime;

    @ExplainAnnotation (explain = "活动结束时间")
    private Long activityEndTime;

    @ExplainAnnotation (explain = "活动地址")
    private String activityAddress;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public String getTopicTypeName() {
        return topicTypeName;
    }

    public void setTopicTypeName(String topicTypeName) {
        this.topicTypeName = topicTypeName;
    }

    public ProjectLifeDTO(String topicId, String topicTitle, String topicImg, Long createTime, String interestTag, Integer topicType, String topicTypeName, boolean hot, Long activityStartTime, Long activityEndTime, String activityAddress) {
        this.topicId = topicId;
        this.topicTitle = topicTitle;
        this.topicImg = topicImg;
        this.createTime = createTime;
        this.interestTag = interestTag;
        this.topicType = topicType;
        this.topicTypeName = topicTypeName;
        this.hot = hot;
        this.activityStartTime = activityStartTime;
        this.activityEndTime = activityEndTime;
        this.activityAddress = activityAddress;
    }

    public ProjectLifeDTO() {
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getInterestTag() {
        return interestTag;
    }

    public void setInterestTag(String interestTag) {
        this.interestTag = interestTag;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public Long getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Long activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Long getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Long activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }
}
