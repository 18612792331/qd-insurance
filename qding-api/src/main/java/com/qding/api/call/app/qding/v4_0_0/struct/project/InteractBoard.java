package com.qding.api.call.app.qding.v4_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefInteractionTagInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/24.
 */
public class InteractBoard extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 6540296410214105162L;

    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;
    
    @ExplainAnnotation(explain = "版块标题")
    private String boardTitle;

    @ExplainAnnotation(explain = "邻里互动标签列表")
    private List<BriefInteractionTagInfo> tagList;
    
    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public List<BriefInteractionTagInfo> getTagList() {
        return tagList;
    }

    public void setTagList(List<BriefInteractionTagInfo> tagList) {
        this.tagList = tagList;
    }

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
    
}
