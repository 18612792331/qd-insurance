package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicDetail;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/9/12.
 */
public class GetTopicsByDesignatedPersonResponseData extends ResponseData{

    private static final long serialVersionUID = 2914909777995522570L;

    @ExplainAnnotation(explain = "话题详情列表")
    private List<TopicDetail> topicList;

    @ExplainAnnotation (explain = "话题总数")
    private Integer totalCount;

    public List<TopicDetail> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicDetail> topicList) {
        this.topicList = topicList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "GetTopicsByDesignatedPersonResponseData{" +
                "topicList=" + topicList +
                ", totalCount=" + totalCount +
                '}';
    }
}
