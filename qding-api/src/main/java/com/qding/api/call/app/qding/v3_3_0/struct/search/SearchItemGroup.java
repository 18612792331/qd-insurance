package com.qding.api.call.app.qding.v3_3_0.struct.search;

import com.qding.api.annotation.ExplainAnnotation;

/**
 * Created by qd on 2017/9/6.
 */
public class SearchItemGroup extends SearchItemPage {

    @ExplainAnnotation(explain = "业务类型",desc = "1:全部，2：乐购，3：服务，4：旅游，5：其他")
    private Integer searchType;

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

}
