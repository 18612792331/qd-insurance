package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.TopicPiazzaDTO;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/2/24.
 */
public class GetTopicPiazzaIndexResponseData extends ResponseData {

    private static final long serialVersionUID = 1898015467984778237L;

    @ExplainAnnotation (explain = "话题列表")
    private List<TopicPiazzaDTO> list;

    @ExplainAnnotation(explain = "排序规则字段")
    private String orderByRule;

    @ExplainAnnotation(explain = "是否有下一页")
    private boolean haveNextPage;

    public String getOrderByRule() {
        return orderByRule;
    }

    public void setOrderByRule(String orderByRule) {
        this.orderByRule = orderByRule;
    }

    public List<TopicPiazzaDTO> getList() {
        return list;
    }

    public void setList(List<TopicPiazzaDTO> list) {
        this.list = list;
    }

    public boolean isHaveNextPage() {
        return haveNextPage;
    }

    public void setHaveNextPage(boolean haveNextPage) {
        this.haveNextPage = haveNextPage;
    }

    @Override
    public String toString() {
        return "GetTopicPiazzaIndexResponseData{" +
                "list=" + list +
                ", orderByRule='" + orderByRule + '\'' +
                ", haveNextPage=" + haveNextPage +
                '}';
    }
}
