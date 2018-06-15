package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/28.
 */
public class CityWideRecommendBoard implements Serializable {

    private static final long serialVersionUID = -4923745508162927254L;


    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;

    @ExplainAnnotation(explain = "板块名称")
    private String title="热卖推荐";

    @ExplainAnnotation(explain = "总记录数")
    private Integer totalCount;

    @ExplainAnnotation(explain = "同城热卖商品信息列表")
    private List<RecommendGood> recommendList;

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<RecommendGood> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(List<RecommendGood> recommendList) {
        this.recommendList = recommendList;
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
