package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/2.
 */
public class ChoicenessTopicInfo extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -6433246362281795197L;

    @ExplainAnnotation(explain = "话题ID")
    private String id;

    @ExplainAnnotation(explain = "话题图片")
    private String imgUrl;

    @ExplainAnnotation(explain = "话题标题")
    private String title;

    @ExplainAnnotation(explain = "精选话题类型",desc="来自subTopicType")
    private Integer type;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

    public String getSubTopicTypeName() {
        return subTopicTypeName;
    }

    public void setSubTopicTypeName(String subTopicTypeName) {
        this.subTopicTypeName = subTopicTypeName;
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
