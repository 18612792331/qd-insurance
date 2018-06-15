package com.qding.api.call.app.qding.v2_8_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicDetail;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/11/22.
 */
public class GetRecommendTopicsByProjectIdResponseData extends ResponseData {

    private static final long serialVersionUID = 8582291920240519665L;

    @ExplainAnnotation(explain = "话题列表")
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
        return "GetRecommendTopicsByProjectIdResponseData{" +
                "topicList=" + topicList +
                ", totalCount=" + totalCount +
                '}';
    }
}
