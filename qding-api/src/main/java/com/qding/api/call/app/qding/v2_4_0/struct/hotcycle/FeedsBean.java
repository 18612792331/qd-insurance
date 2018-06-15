package com.qding.api.call.app.qding.v2_4_0.struct.hotcycle;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.hotcycle.struct.Feed;

import java.util.List;

/**
 * Created by qd on 2016/6/16.
 */
public class FeedsBean  {

    private Integer totalCount;

    private List<Feed> feedList;

    private Integer showCommentSize;

    private Integer showPraiseSize;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Feed> getFeedList() {
        return feedList;
    }

    public void setFeedList(List<Feed> feedList) {
        this.feedList = feedList;
    }

    public Integer getShowCommentSize() {
        return showCommentSize;
    }

    public void setShowCommentSize(Integer showCommentSize) {
        this.showCommentSize = showCommentSize;
    }

    public Integer getShowPraiseSize() {
        return showPraiseSize;
    }

    public void setShowPraiseSize(Integer showPraiseSize) {
        this.showPraiseSize = showPraiseSize;
    }
}
