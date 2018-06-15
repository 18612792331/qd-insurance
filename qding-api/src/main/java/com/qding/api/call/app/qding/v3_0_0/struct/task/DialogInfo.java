package com.qding.api.call.app.qding.v3_0_0.struct.task;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2016/7/26.
 */
public class DialogInfo extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 3044627488918423116L;

    @ExplainAnnotation(explain = "标题")
    private String title;

    @ExplainAnnotation(explain = "标题色值")
    private String titleColor;

    @ExplainAnnotation(explain = "简介")
    private String summary;

    @ExplainAnnotation(explain = "简介色值")
    private String summaryColor;

    @ExplainAnnotation(explain = "活动图片")
    private String imgUrl;

    @ExplainAnnotation(explain = "倒计时时间")
    private Long timeoutInterval;

    @ExplainAnnotation(explain = "展示类型",desc = "1:公告，2：活动")
    private Integer type;

    @ExplainAnnotation(explain = "公告类型",desc = "2：通知:、1：紧急通知、3：社区活动、6：提示、7：社区新闻")
    private Integer noticeType;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummaryColor() {
        return summaryColor;
    }

    public void setSummaryColor(String summaryColor) {
        this.summaryColor = summaryColor;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getTimeoutInterval() {
        return timeoutInterval;
    }

    public void setTimeoutInterval(Long timeoutInterval) {
        this.timeoutInterval = timeoutInterval;
    }

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }
}
