package com.qding.api.call.app.qding.v2_8_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefTheme;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicDetail;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/11/22.
 */
public class GetNearbyTopicsByThemeIdResponseData extends ResponseData {

    private static final long serialVersionUID = -7862803586378412088L;


    @ExplainAnnotation(explain = "话题列表")
    private List<TopicDetail> topicList;

    @ExplainAnnotation(explain = "主题简要信息")
    private BriefTheme themeInfo;

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

    public BriefTheme getThemeInfo() {
        return themeInfo;
    }

    public void setThemeInfo(BriefTheme themeInfo) {
        this.themeInfo = themeInfo;
    }

    @Override
    public String toString() {
        return "GetNearbyTopicsByThemeIdResponseData{" +
                "topicList=" + topicList +
                ", themeInfo=" + themeInfo +
                ", totalCount=" + totalCount +
                '}';
    }
}
