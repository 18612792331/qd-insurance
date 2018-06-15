package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.ThemeTagInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/9/9.
 */
public class TopicDetail implements Serializable {

    private static final long serialVersionUID = 4762608030286375401L;

    @ExplainAnnotation(explain = "标签信息")
    private ThemeTagInfo tag;

    @ExplainAnnotation(explain = "话题ID")
    private String topicId;

    @ExplainAnnotation(explain = "话题状态",desc = "1:发布，2：待审")
    private Integer status;

    @ExplainAnnotation(explain = "发布人账号ID")
    private String userId;

    @ExplainAnnotation(explain = "发帖人简要信息")
    private BriefMember memberInfo;

    @ExplainAnnotation(explain = "主题简要信息")
    private BriefTheme themeInfo;

    @ExplainAnnotation(explain = "发布城市")
    private String cityName;

    @ExplainAnnotation(explain = "发布社区")
    private String projectName;

    @ExplainAnnotation(explain = "创建时间")
    private Long createTime;

    @ExplainAnnotation(explain = "展示用时间")
    private String showTime;

    @ExplainAnnotation(explain = "话题标题")
    private String topicTitle;

    @ExplainAnnotation(explain = "话题类型", desc = "1:普通 2:投票 3:报名活动 4:营销banner 5:营销url")
    private Integer topicType;

    @ExplainAnnotation(explain = "话题简述")
    private String topicDesc;

    @ExplainAnnotation(explain = "话题全文")
    private String topicContent;

    @ExplainAnnotation(explain = "话题图片")
    private List<String> topicImage;

    @ExplainAnnotation(explain = "投票话题信息")
    private VoteInfo voteInfo;

    @ExplainAnnotation(explain = "报名话题信息")
    private ActivityInfo activityInfo;

    @ExplainAnnotation(explain = "总评论数")
    private Integer commentCount;

    @ExplainAnnotation(explain = "总点赞数")
    private Integer praiseCount;

    @ExplainAnnotation(explain = "评论信息列表")
    private List<TopicComment> topicCommentList;

    @ExplainAnnotation(explain = "点赞用户列表")
    private List<BriefMember> topicPraiseList;

    @ExplainAnnotation(explain = "是否点过赞",desc = "1:点过，0：未点过")
    private Integer isPraise = Integer.valueOf(0);

    @ExplainAnnotation(explain = "营销类话题跳转模型", desc = "banner类型btnName为空")
    private BtnSkip btnSkip;

    public Integer getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(Integer isPraise) {
        this.isPraise = isPraise;
    }

    public BtnSkip getBtnSkip() {
        return btnSkip;
    }

    public void setBtnSkip(BtnSkip btnSkip) {
        this.btnSkip = btnSkip;
    }

    public List<BriefMember> getTopicPraiseList() {
        return topicPraiseList;
    }

    public void setTopicPraiseList(List<BriefMember> topicPraiseList) {
        this.topicPraiseList = topicPraiseList;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public BriefMember getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(BriefMember memberInfo) {
        this.memberInfo = memberInfo;
    }

    public BriefTheme getThemeInfo() {
        return themeInfo;
    }

    public void setThemeInfo(BriefTheme themeInfo) {
        this.themeInfo = themeInfo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
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

    public VoteInfo getVoteInfo() {
        return voteInfo;
    }

    public void setVoteInfo(VoteInfo voteInfo) {
        this.voteInfo = voteInfo;
    }

    public ActivityInfo getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(ActivityInfo activityInfo) {
        this.activityInfo = activityInfo;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public List<TopicComment> getTopicCommentList() {
        return topicCommentList;
    }

    public void setTopicCommentList(List<TopicComment> topicCommentList) {
        this.topicCommentList = topicCommentList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public ThemeTagInfo getTag() {
        return tag;
    }

    public void setTag(ThemeTagInfo tag) {
        this.tag = tag;
    }
}
