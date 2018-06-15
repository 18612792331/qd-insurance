package com.qding.api.call.app.qding.v2_4_0.struct.hotcycle.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2016/6/16.
 */
public class GetFeedDetailListRequest extends BaseRequest {

    private static final long serialVersionUID = -6073553703608635787L;

    @ExplainAnnotation (explain = "每页显示数")
    private Integer pageSize;

    @ExplainAnnotation (explain = "切入点的FeedId")
    private String feedId;

    @ExplainAnnotation (explain = "切屏方式",desc = "1:左切,2:右切,3:取中一条")
    private Integer changeType;

    @ExplainAnnotation (explain = "场景类型",desc = "1:广场，2：标签，3：活动，4：群朋友圈")
    private Integer type;

    @ExplainAnnotation (explain = "通用ID",desc = "广场类型传社区ID，标签类型传标签ID,活动类型传活动ID,我的邻聚类型传userId，群朋友圈传群ID")
    private String id;

    @ExplainAnnotation (explain = "评论展示数")
    private Integer showCommentSize;

    @ExplainAnnotation (explain = "点赞展示数")
    private Integer showPraiseSize;

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

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    @Override
    public String toString() {
        return "GetFeedDetailListRequest{" +
                "pageSize=" + pageSize +
                ", feedId='" + feedId + '\'' +
                ", changeType=" + changeType +
                ", type=" + type +
                ", id='" + id + '\'' +
                ", showCommentSize=" + showCommentSize +
                ", showPraiseSize=" + showPraiseSize +
                '}';
    }
}
