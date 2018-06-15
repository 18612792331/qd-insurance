package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.ActivityPiazzaDTO;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/2/24.
 */
public class GetActivityPiazzaIndexResponseData extends ResponseData {

    private static final long serialVersionUID = 1442798378355958708L;

    @ExplainAnnotation (explain = "活动列表")
    private List<ActivityPiazzaDTO> list;

    @ExplainAnnotation (explain = "日历首页跳转")
    private String skipModel;

    @ExplainAnnotation(explain = "排序规则字段")
    private String orderByRule;

    @ExplainAnnotation(explain = "是否有下一页")
    private boolean haveNextPage;


    public String getSkipModel() {
        return skipModel;
    }

    public void setSkipModel(String skipModel) {
        this.skipModel = skipModel;
    }

    public String getOrderByRule() {
        return orderByRule;
    }

    public void setOrderByRule(String orderByRule) {
        this.orderByRule = orderByRule;
    }

    public boolean isHaveNextPage() {
        return haveNextPage;
    }

    public void setHaveNextPage(boolean haveNextPage) {
        this.haveNextPage = haveNextPage;
    }

    public List<ActivityPiazzaDTO> getList() {
        return list;
    }

    public void setList(List<ActivityPiazzaDTO> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "GetActivityPiazzaIndexResponseData{" +
                "list=" + list +
                ", skipModel='" + skipModel + '\'' +
                ", orderByRule='" + orderByRule + '\'' +
                ", haveNextPage=" + haveNextPage +
                '}';
    }
}
