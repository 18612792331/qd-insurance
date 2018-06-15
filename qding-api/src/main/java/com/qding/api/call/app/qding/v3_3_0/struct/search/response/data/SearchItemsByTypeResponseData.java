package com.qding.api.call.app.qding.v3_3_0.struct.search.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.search.SearchItem;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/9/7.
 */
public class SearchItemsByTypeResponseData extends ResponseData {

    private static final long serialVersionUID = 3175360769369836167L;

    @ExplainAnnotation(explain = "搜索结果集")
    private List<SearchItem> list;

    @ExplainAnnotation (explain = "搜索结果数")
    private Integer totalCount;

    public List<SearchItem> getList() {
        return list;
    }

    public void setList(List<SearchItem> list) {
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
