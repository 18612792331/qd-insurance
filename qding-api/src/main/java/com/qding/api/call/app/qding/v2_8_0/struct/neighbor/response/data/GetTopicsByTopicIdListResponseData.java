package com.qding.api.call.app.qding.v2_8_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicDetail;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/11/22.
 */
public class GetTopicsByTopicIdListResponseData extends ResponseData {


    @ExplainAnnotation(explain = "话题列表")
    private List<TopicDetail> topicList;

    public List<TopicDetail> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicDetail> topicList) {
        this.topicList = topicList;
    }


    @Override
    public String toString() {
        return "GetTopicsByTopicIdListResponseData{" +
                ", topicList=" + topicList +
                '}';
    }
}
