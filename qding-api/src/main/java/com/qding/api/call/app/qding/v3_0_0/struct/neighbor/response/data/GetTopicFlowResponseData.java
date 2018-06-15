package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.Tag;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.TopicCommon;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/3/4.
 */
public class GetTopicFlowResponseData extends ResponseData {

    private static final long serialVersionUID = 6843397143075031178L;

    @ExplainAnnotation (explain = "帖子列表")
    private List<TopicCommon> list;

    @ExplainAnnotation(explain = "标签信息")
    private Tag tag;

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

    public List<TopicCommon> getList() {
        return list;
    }

    public void setList(List<TopicCommon> list) {
        this.list = list;
    }

    public boolean isHaveNextPage() {
        return haveNextPage;
    }

    public void setHaveNextPage(boolean haveNextPage) {
        this.haveNextPage = haveNextPage;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "GetTopicFlowResponseData{" +
                "list=" + list +
                ", tag=" + tag +
                ", orderByRule='" + orderByRule + '\'' +
                ", haveNextPage=" + haveNextPage +
                '}';
    }
}
