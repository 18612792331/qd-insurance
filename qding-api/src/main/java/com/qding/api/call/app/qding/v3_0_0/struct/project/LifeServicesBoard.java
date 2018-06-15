package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.BoardImg;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SalesBoard;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/28.
 */
public class LifeServicesBoard implements Serializable {

    private static final long serialVersionUID = -9211780979253312029L;

    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;

    @ExplainAnnotation(explain = "服务板块名称")
    private String title = "生活服务";

    @ExplainAnnotation(explain = "生活服务列表")
    private List<BoardImg> lifeServicesList;

    @ExplainAnnotation(explain = "居家服务营销位")
    private SalesBoard lifeServiceMarket;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BoardImg> getLifeServicesList() {
        return lifeServicesList;
    }

    public void setLifeServicesList(List<BoardImg> lifeServicesList) {
        this.lifeServicesList = lifeServicesList;
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

    public SalesBoard getLifeServiceMarket() {
        return lifeServiceMarket;
    }

    public void setLifeServiceMarket(SalesBoard lifeServiceMarket) {
        this.lifeServiceMarket = lifeServiceMarket;
    }
}
