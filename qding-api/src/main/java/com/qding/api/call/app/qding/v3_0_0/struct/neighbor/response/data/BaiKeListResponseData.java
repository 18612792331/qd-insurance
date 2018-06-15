package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.BriefEncyclopedia;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/3/2.
 */
public class BaiKeListResponseData extends ResponseData {

    private static final long serialVersionUID = -456833869765190357L;

    @ExplainAnnotation (explain = "百科列表")
    private List<BriefEncyclopedia> encyclopediaList;

    @ExplainAnnotation(explain = "是否有下一页")
    private boolean haveNextPage;

    @ExplainAnnotation(explain = "排序规则字段")
    private String orderByRule;

    public List<BriefEncyclopedia> getEncyclopediaList() {
        return encyclopediaList;
    }

    public void setEncyclopediaList(List<BriefEncyclopedia> encyclopediaList) {
        this.encyclopediaList = encyclopediaList;
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
