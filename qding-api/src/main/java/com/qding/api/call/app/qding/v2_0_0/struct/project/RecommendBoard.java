package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/12/21.
 */
public class RecommendBoard implements Serializable {

    private static final long serialVersionUID = -6095029715677069513L;

    @ExplainAnnotation(explain = "板块名称")
    private String name="猜你喜欢";

    @ExplainAnnotation(explain = "总记录数")
    private Integer totalCount;

    @ExplainAnnotation(explain = "推荐商品信息列表")
    private List<RecommendGood> recommendList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RecommendGood> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(List<RecommendGood> recommendList) {
        this.recommendList = recommendList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
