package com.qding.api.call.app.qding.v3_3_0.struct.search;

import com.qding.api.annotation.ExplainAnnotation;

/**
 * Created by qd on 2017/9/6.
 */
public class SearchItemGroupCount {

    @ExplainAnnotation(explain = "业务类型")
    private Integer businessType;

    @ExplainAnnotation (explain = "搜索结果数")
    private Integer totalCount;

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
