package com.qding.api.call.app.qding.v3_3_0.struct.search.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/9/7.
 */
public class SearchKeyWordItemsResponseData extends ResponseData {

    private static final long serialVersionUID = -5755979351519905335L;

    @ExplainAnnotation (explain = "关键词")
    private List<String> list;

    public List<String>getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SearchKeyWordItemsResponseData{" +
                "list=" + list +
                '}';
    }
}
