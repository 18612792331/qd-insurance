package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/28.
 */
public class QualityLifeBoard implements Serializable {

    private static final long serialVersionUID = -5169870153381509609L;


    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;

    @ExplainAnnotation (explain = "品质生活板块名称")
    private String title ="品质生活";

    @ExplainAnnotation (explain = "品质生活列表")
    private List<QualityLifeDTO> qualityLifeList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<QualityLifeDTO> getQualityLifeList() {
        return qualityLifeList;
    }

    public void setQualityLifeList(List<QualityLifeDTO> qualityLifeList) {
        this.qualityLifeList = qualityLifeList;
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
}
