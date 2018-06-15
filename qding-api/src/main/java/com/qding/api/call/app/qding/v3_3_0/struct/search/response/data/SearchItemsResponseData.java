package com.qding.api.call.app.qding.v3_3_0.struct.search.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.search.SearchItemGroup;
import com.qding.api.call.app.qding.v3_3_0.struct.search.SearchItemGroupCount;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/9/6.
 */
public class SearchItemsResponseData extends ResponseData {


    @ExplainAnnotation(explain = "业务查询结果集分组")
    private List<SearchItemGroup> searchList;

    public List<SearchItemGroup> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchItemGroup> searchList) {
        this.searchList = searchList;
    }


    @Override
    public String toString() {
        return "SearchItemsResponseData{" +
                ", searchList=" + searchList +
                '}';
    }
}
