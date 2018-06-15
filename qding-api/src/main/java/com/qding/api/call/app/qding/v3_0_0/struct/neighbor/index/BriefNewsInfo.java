package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/2/24.
 */
public class BriefNewsInfo extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 8975397195454751781L;

    @ExplainAnnotation (explain = "新闻ID")
    private String id;

    @ExplainAnnotation (explain = "新闻类型")
    private String newsType;

    @ExplainAnnotation (explain = "新闻标题")
    private String title;

    @ExplainAnnotation (explain = "新闻图片")
    private String imgUrl;

    @ExplainAnnotation(explain = "新闻时间")
    private Long publishTime;
    
    @ExplainAnnotation (explain = "是否new  1 是 ， 0 否")
    private Integer isNew;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public BriefNewsInfo(){

    }
    
	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public BriefNewsInfo(String id, String newsType, String title, String imgUrl, Long publishTime) {
        this.id = id;
        this.newsType = newsType;
        this.title = title;
        this.imgUrl = imgUrl;
        this.publishTime = publishTime;
    }

	@Override
	public String toString() {
		return "BriefNewsInfo [id=" + id + ", newsType=" + newsType
				+ ", title=" + title + ", imgUrl=" + imgUrl + ", publishTime="
				+ publishTime + ", isNew=" + isNew + "]";
	}
	
	
	
	
	
}
