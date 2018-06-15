package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefNewsInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/28.
 */
public class ProjectLifeBoard extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -435311524059236729L;

    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;

    @ExplainAnnotation (explain = "板块标题")
    private String title = "社区生活";

    @ExplainAnnotation(explain = "新闻列表")
    private List<BriefNewsInfo> newsList;

    @ExplainAnnotation (explain = "社区生活板块话题列表")
    private List<ProjectLifeDTO> topicList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProjectLifeDTO> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<ProjectLifeDTO> topicList) {
        this.topicList = topicList;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public List<BriefNewsInfo> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<BriefNewsInfo> newsList) {
        this.newsList = newsList;
    }
}
