package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/9/9.
 */
@Validate
public class GetNeighborIndexRequest extends BaseRequest {

    private static final long serialVersionUID = -951679356376470233L;

    @ExplainAnnotation (explain = "社区ID")
    @NotNullValidate
    private String projectId;

    @ExplainAnnotation (explain = "会员ID")
    private String memberId;

    @ExplainAnnotation (explain = "首屏主题站显示个数",desc = "这里的个数设置和主题站分页每页查询数一致，且当前默认为第一页")
    private Integer showThemeSize = Integer.valueOf(100);

    @ExplainAnnotation (explain = "首屏话题显示个数",desc = "这里的个数设置和话题瀑布刘分页每页查询数一致，且当前默认为第一页")
    @NotNullValidate
    private Integer showTopicSize = Integer.valueOf(10);

    @ExplainAnnotation (explain = "默认显示评论数")
    @NotNullValidate
    private Integer showCommentSize = Integer.valueOf(3);

    @ExplainAnnotation (explain = "默认显示点赞人员列表数")
    @NotNullValidate
    private Integer showPraiseSize= Integer.valueOf(7);

    @ExplainAnnotation (explain = "刷新时间戳")
    @NotNullValidate
    private Long lastRefreshTime = Long.valueOf(0);

    public Long getLastRefreshTime() {
        return lastRefreshTime;
    }

    public void setLastRefreshTime(Long lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
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


    public Integer getShowThemeSize() {
        return showThemeSize;
    }

    public void setShowThemeSize(Integer showThemeSize) {
        this.showThemeSize = showThemeSize;
    }

    public Integer getShowTopicSize() {
        return showTopicSize;
    }

    public void setShowTopicSize(Integer showTopicSize) {
        this.showTopicSize = showTopicSize;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "GetNeighborIndexRequest{" +
                "projectId='" + projectId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", showThemeSize=" + showThemeSize +
                ", showTopicSize=" + showTopicSize +
                ", showCommentSize=" + showCommentSize +
                ", showPraiseSize=" + showPraiseSize +
                ", lastRefreshTime=" + lastRefreshTime +
                '}';
    }
}
