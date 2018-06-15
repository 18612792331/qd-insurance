package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/24.
 */
public class NewsBoard extends SkipUrl implements Serializable {

    @ExplainAnnotation(explain = "版块标题")
    private String boardTitle = "社区新闻";

    @ExplainAnnotation(explain = "新闻列表")
    private List<BriefNewsInfo> newsList;


    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public List<BriefNewsInfo> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<BriefNewsInfo> newsList) {
        this.newsList = newsList;
    }

}
