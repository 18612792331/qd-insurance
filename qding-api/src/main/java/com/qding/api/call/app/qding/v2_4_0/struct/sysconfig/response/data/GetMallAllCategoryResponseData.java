package com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.CategoryInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;


public class GetMallAllCategoryResponseData extends ResponseData {

    private static final long serialVersionUID = 8722040248346951635L;

    @ExplainAnnotation(explain = "品类")
    private List<CategoryInfo> categoryInfoList;

    public List<CategoryInfo> getCategoryInfoList() {
        return categoryInfoList;
    }

    public void setCategoryInfoList(List<CategoryInfo> categoryInfoList) {
        this.categoryInfoList = categoryInfoList;
    }

    @Override
    public String toString() {
        return "GetMallAllCategoryResponseData{" +
                "categoryInfoList=" + categoryInfoList +
                '}';
    }
}
