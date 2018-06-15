package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefActivity;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefVote;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicImag;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;

/**
 * Created by qd on 2016/9/9.
 */
@Validate
public class AddTopicRequest extends BaseRequest {

    private static final long serialVersionUID = 3878446217991049529L;

    @ExplainAnnotation(explain = "社区Id")
    private Long projectId;

    @NotNullValidate
    @ExplainAnnotation(explain = "会员ID")
    private String memberId;

    @NotNullValidate
    @ExplainAnnotation(explain = "账户ID")
    private String userId;

    @ExplainAnnotation(explain = "主题ID")
    private String themeId;

    @ExplainAnnotation(explain = "话题标题", desc = "目前只针对报名类型有标题")
    private String topicTitle;

    @ExplainAnnotation(explain = "话题类型", desc = "话题类型 1普通 2投票 3报名活动 4营销banner 5营销url")
    private Integer topicType;

    @ExplainAnnotation(explain = "话题信息内容")
    private String topicContent;

    @ExplainAnnotation(explain = "图片列表")
    private List<String> topicImage;

    @ExplainAnnotation(explain = "报名活动")
    private BriefActivity activity;

    @ExplainAnnotation(explain = "投票信息")
    private BriefVote vote;

    @ExplainAnnotation(explain = "标签ID")
    private String tagId;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public List<String> getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(List<String> topicImage) {
        this.topicImage = topicImage;
    }

    public BriefActivity getActivity() {
        return activity;
    }

    public void setActivity(BriefActivity activity) {
        this.activity = activity;
    }

    public BriefVote getVote() {
        return vote;
    }

    public void setVote(BriefVote vote) {
        this.vote = vote;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AddTopicRequest{" +
                "projectId=" + projectId +
                ", memberId='" + memberId + '\'' +
                ", userId='" + userId + '\'' +
                ", themeId='" + themeId + '\'' +
                ", topicTitle='" + topicTitle + '\'' +
                ", topicType=" + topicType +
                ", topicContent='" + topicContent + '\'' +
                ", topicImage=" + topicImage +
                ", activity=" + activity +
                ", vote=" + vote +
                '}';
    }
}
