package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/4.
 */
public class TopicPiazzaDTO extends SkipUrl implements Serializable {


    @ExplainAnnotation (explain = "话题ID")
    private String topicId;

    @ExplainAnnotation (explain = "头图")
    private String topImg;

    @ExplainAnnotation (explain = "子类型",desc = "1：晒图话题，2：讨论型话题，3：投票型话题, 4：报名")
    private Integer subTopicType;

    @ExplainAnnotation (explain = "标题")
    private String topicTitle;

    @ExplainAnnotation (explain = "导语")
    private String introduction;

    @ExplainAnnotation (explain = "人数")
    private Integer peopleNum;

    @ExplainAnnotation (explain = "子类型名称")
    private String subTopicTypeName;

    @ExplainAnnotation (explain = "开始时间")
    private Long beginTime;

    @ExplainAnnotation (explain = "结束时间")
    private Long endTime;

    @ExplainAnnotation (explain = "简介")
    private String description;

    @ExplainAnnotation (explain = "是否已结束")
    private boolean ended;

    public String getSubTopicTypeName() {
        return subTopicTypeName;
    }

    public void setSubTopicTypeName(String subTopicTypeName) {
        this.subTopicTypeName = subTopicTypeName;
    }

    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    public Integer getSubTopicType() {
        return subTopicType;
    }

    public void setSubTopicType(Integer subTopicType) {
        this.subTopicType = subTopicType;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }
}
