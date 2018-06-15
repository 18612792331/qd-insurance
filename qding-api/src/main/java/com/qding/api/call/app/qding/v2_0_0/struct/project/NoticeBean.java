package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2015/12/16.
 */
public class NoticeBean implements Serializable {

    private static final long serialVersionUID = 4687404891880689962L;

    @ExplainAnnotation(explain = "公告ID")
    private Long id;

    @ExplainAnnotation(explain = "公告图片")
    private String imageUrl[];

    @ExplainAnnotation(explain = "标题")
    private String title;

    @ExplainAnnotation(explain = "公告类型",desc = "2：通知:、1：紧急通知、3：社区活动、6：提示、7：社区新闻")
    private Integer noticeType;

    @ExplainAnnotation(explain = "发布时间")
    private String publishTime;

    @ExplainAnnotation(explain = "url",desc = "2.0版本只针对社区活动")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
