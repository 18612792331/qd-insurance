package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefTheme;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/9/9.
 */
public class GetBriefThemesByPagingResponseData extends ResponseData {

    private static final long serialVersionUID = 2972564142668218678L;

    @ExplainAnnotation(explain = "简要主题信息列表")
    private List<BriefTheme> themeList;

    @ExplainAnnotation(explain = "总记录数")
    private Integer totalCount;

    public List<BriefTheme> getThemeList() {
        return themeList;
    }

    public void setThemeList(List<BriefTheme> themeList) {
        this.themeList = themeList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "GetBriefThemesByPagingResponseData{" +
                "themeList=" + themeList +
                ", totalCount=" + totalCount +
                '}';
    }
}
