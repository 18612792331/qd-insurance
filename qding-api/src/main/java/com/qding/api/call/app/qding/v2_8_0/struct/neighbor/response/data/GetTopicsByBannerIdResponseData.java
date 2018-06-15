package com.qding.api.call.app.qding.v2_8_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicDetail;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/12/2.
 */
public class GetTopicsByBannerIdResponseData  extends ResponseData {


    @ExplainAnnotation(explain = "banner标题")
    private String bannerTitle;

    @ExplainAnnotation(explain = "话题列表")
    private List<TopicDetail> topicList;

    @ExplainAnnotation(explain = "总条数")
    private Integer totalCount;

    public List<TopicDetail> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicDetail> topicList) {
        this.topicList = topicList;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "GetTopicsByBannerIdResponseData{" +
                "bannerTitle='" + bannerTitle + '\'' +
                ", topicList=" + topicList +
                ", totalCount=" + totalCount +
                '}';
    }
}
