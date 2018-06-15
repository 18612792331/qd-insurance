package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefNewsInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/2/23.
 */
public class GetNewsListResponseData extends ResponseData {

    private static final long serialVersionUID = 2108010926604691864L;

    @ExplainAnnotation(explain = "新闻列表")
    private List<BriefNewsInfo> newsList;

    @ExplainAnnotation(explain = "是否有下一页")
    private boolean haveNextPage;

    @ExplainAnnotation(explain = "排序规则字段")
    private String orderByRule;

    public List<BriefNewsInfo> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<BriefNewsInfo> newsList) {
        this.newsList = newsList;
    }

    public boolean isHaveNextPage() {
        return haveNextPage;
    }

    public void setHaveNextPage(boolean haveNextPage) {
        this.haveNextPage = haveNextPage;
    }

    public String getOrderByRule() {
        return orderByRule;
    }

    public void setOrderByRule(String orderByRule) {
        this.orderByRule = orderByRule;
    }
}
