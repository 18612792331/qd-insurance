package com.qding.api.call.app.qding.v3_3_0.struct.search;

import com.qding.api.annotation.ExplainAnnotation;

import java.util.List;

/**
 * Created by qd on 2017/9/13.
 */
public class SearchItemPage {

    @ExplainAnnotation(explain = "查询总数")
    private  Integer totalCount;

    @ExplainAnnotation (explain = "搜索结果集")
    private List<SearchItem> list;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<SearchItem> getList() {
        return list;
    }

    public void setList(List<SearchItem> list) {
        this.list = list;
    }
}
