package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefTheme;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicDetail;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.NeighborBanner;
import com.qding.api.struct.ResponseData;
import java.util.List;

/**
 * Created by qd on 2016/9/9.
 */
public class GetNeighborIndexResponseData extends ResponseData {

    private static final long serialVersionUID = 7575340753362086921L;

    @ExplainAnnotation (explain = "简要主题信息列表")
    private List<BriefTheme> themeList;

    @ExplainAnnotation (explain = "邻聚Banner列表")
    private List<NeighborBanner> bannerList;

    @ExplainAnnotation (explain = "话题列表")
    private List<TopicDetail> topicList;

    @ExplainAnnotation (explain = "主题总数")
    private Integer themeCount;

    @ExplainAnnotation (explain = "话题总数")
    private Integer totalCount;

    @ExplainAnnotation (explain = "新增话题总数")
    private Integer newTopicCount;

    @ExplainAnnotation (explain = "刷新时间戳")
    private Long lastRefreshTime;

    public List<BriefTheme> getThemeList() {
        return themeList;
    }

    public void setThemeList(List<BriefTheme> themeList) {
        this.themeList = themeList;
    }

    public List<TopicDetail> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicDetail> topicList) {
        this.topicList = topicList;
    }

    public Integer getThemeCount() {
        return themeCount;
    }

    public void setThemeCount(Integer themeCount) {
        this.themeCount = themeCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getNewTopicCount() {
        return newTopicCount;
    }

    public void setNewTopicCount(Integer newTopicCount) {
        this.newTopicCount = newTopicCount;
    }

    public Long getLastRefreshTime() {
        return lastRefreshTime;
    }

    public void setLastRefreshTime(Long lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }

    public List<NeighborBanner> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<NeighborBanner> bannerList) {
        this.bannerList = bannerList;
    }

    @Override
    public String toString() {
        return "GetNeighborIndexResponseData{" +
                "themeList=" + themeList +
                ", bannerList=" + bannerList +
                ", topicList=" + topicList +
                ", themeCount=" + themeCount +
                ", totalCount=" + totalCount +
                ", newTopicCount=" + newTopicCount +
                ", lastRefreshTime=" + lastRefreshTime +
                '}';
    }
}
