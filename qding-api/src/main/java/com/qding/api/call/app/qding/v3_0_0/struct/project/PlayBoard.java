package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.BoardImg;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;
import java.util.List;

/**
 * 出游玩乐
 * Created by jinhaishan on 17/7/13.
 */
public class PlayBoard extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 5020855226413162789L;

    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;

    @ExplainAnnotation (explain = "板块标题")
    private String title = "出游玩乐";

    @ExplainAnnotation(explain = "出游玩乐列表")
    private List<BoardImg> playList;

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BoardImg> getPlayList() {
        return playList;
    }

    public void setPlayList(List<BoardImg> playList) {
        this.playList = playList;
    }
}
