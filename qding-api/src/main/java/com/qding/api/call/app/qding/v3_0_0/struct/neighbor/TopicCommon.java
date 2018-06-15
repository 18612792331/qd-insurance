package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefMember;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.ReferenceTopicDto;
import com.qding.api.util.APIPropertiesClient;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Created by qd on 2017/3/4.
 */
public class TopicCommon extends SkipUrl implements Serializable {

    @ExplainAnnotation(explain = "标签信息")
    private Tag tag;

    @ExplainAnnotation(explain = "父话题ID")
    private String parentTopicId;

    @ExplainAnnotation(explain = "帖子ID")
    private String topicId;

    @ExplainAnnotation(explain = "话题状态",desc = "1:发布")
    private Integer status;

    @ExplainAnnotation(explain = "发布人账号ID")
    private String userId;

    @ExplainAnnotation(explain = "发帖人简要信息")
    private BriefMember memberInfo;

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

    @ExplainAnnotation(explain = "话题类型", desc = "1:普通 2:投票 3:报名活动")
    private Integer topicType;

    @ExplainAnnotation(explain = "新版子分类",desc = "1：晒图话题，2：讨论型话题，3：投票型话题, 4：报名  5:邻里互动，6,：新闻，7：百科")
    private Integer subTopicType;

    @ExplainAnnotation(explain = "话题简述")
    private String topicDesc;

    @ExplainAnnotation(explain = "话题全文")
    private String topicContent;
    
    @ExplainAnnotation(explain = "话题报名导语")
    private String introduction;

    @ExplainAnnotation(explain = "话题图片")
    private List<String> topicImage;

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
    
    @ExplainAnnotation (explain = "是否折叠",desc="true折叠、false展开 ")
    private Boolean isCollapse=false;


    @ExplainAnnotation(explain = "分享url")
    private String shareUrl=APIPropertiesClient.getTopicShareUrl();

    @ExplainAnnotation(explain = "话题时间维度状态", desc = "0:未开始，1：进行中，2：已结束")
    private Integer step;
    
    @ExplainAnnotation(explain = "百科新闻被收藏的总数量", desc="3.3新增")
    private Integer favoriteNum=0;
    
    @ExplainAnnotation(explain = "是否已收藏过 1:已收藏，0：未收藏",desc = "3.3新增")
    private Integer isFavorite = Integer.valueOf(0);

    @ExplainAnnotation(explain = "是否展示当前社区下发帖", desc = "只针对话题类型，1:是，0:否")
    private Integer showCurProjectTopics;
    
    @ExplainAnnotation(explain = "引用贴 主要是后台运营贴 3.3 新增",desc="3.3以前版本没有")
    private ReferenceTopicDto reference;

    public Integer getShowCurProjectTopics() {
        return showCurProjectTopics;
    }

    public void setShowCurProjectTopics(Integer showCurProjectTopics) {
        this.showCurProjectTopics = showCurProjectTopics;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getParentTopicId() {
        return parentTopicId;
    }

    public void setParentTopicId(String parentTopicId) {
        this.parentTopicId = parentTopicId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BriefMember getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(BriefMember memberInfo) {
        this.memberInfo = memberInfo;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
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

    public Integer getSubTopicType() {
        return subTopicType;
    }

    public void setSubTopicType(Integer subTopicType) {
        this.subTopicType = subTopicType;
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

    public List<BriefMember> getTopicPraiseList() {
        return topicPraiseList;
    }

    public void setTopicPraiseList(List<BriefMember> topicPraiseList) {
        this.topicPraiseList = topicPraiseList;
    }

    public Integer getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(Integer isPraise) {
        this.isPraise = isPraise;
    }

    public String getShareUrl() {
    	if(StringUtils.isNotBlank(topicId)){
    		return shareUrl+topicId;
    	}
        return null;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

	public Boolean getIsCollapse() {
		return isCollapse;
	}

	public void setIsCollapse(Boolean isCollapse) {
		this.isCollapse = isCollapse;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(Integer favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public Integer getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Integer isFavorite) {
		this.isFavorite = isFavorite;
	}

	public ReferenceTopicDto getReference() {
		return reference;
	}

	public void setReference(ReferenceTopicDto reference) {
		this.reference = reference;
	}
    
	
	
    
    
}
