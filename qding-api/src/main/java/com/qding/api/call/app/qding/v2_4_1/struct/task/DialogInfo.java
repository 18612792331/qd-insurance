package com.qding.api.call.app.qding.v2_4_1.struct.task;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/7/26.
 */
public class DialogInfo implements Serializable {

    private static final long serialVersionUID = 3044627488918423116L;

    @ExplainAnnotation(explain = "标题")
    private String title;

    @ExplainAnnotation(explain = "标题色值")
    private String titleColor;

    @ExplainAnnotation(explain = "简介")
    private String summary;

    @ExplainAnnotation(explain = "简介色值")
    private String summaryColor;

    @ExplainAnnotation(explain = "活动图片",desc = "建议大小 320*480px")
    private String imgUrl;

    @ExplainAnnotation(explain = "门禁任务按钮跳转模型")
    private List<TaskBtnSkip> taskBtnSkipList;

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

    public List<TaskBtnSkip> getTaskBtnSkipList() {
        return taskBtnSkipList;
    }

    public void setTaskBtnSkipList(List<TaskBtnSkip> taskBtnSkipList) {
        this.taskBtnSkipList = taskBtnSkipList;
    }
}
