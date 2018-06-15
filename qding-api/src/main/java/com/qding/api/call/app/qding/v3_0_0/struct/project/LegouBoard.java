package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.util.List;

/**
 * Created by qd on 2017/9/7.
 */
public class LegouBoard {


    @ExplainAnnotation (explain = "板块标题")
    private String title = "乐购商城";

    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;

    @ExplainAnnotation(explain = "乐购商城运营位")
    private List<LegouDTO> list;

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

    public List<LegouDTO> getList() {
        return list;
    }

    public void setList(List<LegouDTO> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
