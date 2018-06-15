package com.qding.api.call.app.qding.v4_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.util.List;

/**
 * Created by qd on 2017/9/7.
 */
public class CustomBoard {


    @ExplainAnnotation (explain = "板块标题")
    private String title;

    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;
    
    @ExplainAnnotation(explain = "主推荐位")
    private CustomDTO custom;

    @ExplainAnnotation(explain = "副推荐位")
    private List<CustomDTO> list;

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

	public CustomDTO getCustom() {
		return custom;
	}

	public void setCustom(CustomDTO custom) {
		this.custom = custom;
	}

	public List<CustomDTO> getList() {
		return list;
	}

	public void setList(List<CustomDTO> list) {
		this.list = list;
	}
    
    
}
