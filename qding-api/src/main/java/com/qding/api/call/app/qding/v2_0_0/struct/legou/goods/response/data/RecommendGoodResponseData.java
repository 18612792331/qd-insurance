package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.RecommendGood;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/1/5.
 */
public class RecommendGoodResponseData extends ResponseData {

    private static final long serialVersionUID = -3890926019497719521L;

    @ExplainAnnotation(explain = "板块标题")
    private String name="猜你喜欢";

    @ExplainAnnotation(explain = "总记录数")
    private Integer totalCount;

    @ExplainAnnotation(explain = "推荐商品列表")
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
