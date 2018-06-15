package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;

/**
 * Created by qd on 2017/2/24.
 */
@Validate
public class PublishTopicRequest extends BaseRequest {

    private static final long serialVersionUID = -7182175965738505507L;

    @ExplainAnnotation(explain = "话题信息内容")
    private String topicContent;

    @ExplainAnnotation(explain = "图片列表")
    private List<String> topicImage;

    @ExplainAnnotation(explain = "话题标签",desc = "邻里互动常态标签")
    private String tagId;

    @NotNullValidate
    @ExplainAnnotation(explain = "新版子分类",desc = "1：晒图话题，2：讨论型话题，3：投票型话题, 4：报名  5:邻里互动，6,：新闻，7：百科")
    private Integer subTopicType;

    @ExplainAnnotation(explain = "跟帖操作关联的父帖子id",desc = "邻里互动不需传")
    private String parentTopicId;

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

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public Integer getSubTopicType() {
        return subTopicType;
    }

    public void setSubTopicType(Integer subTopicType) {
        this.subTopicType = subTopicType;
    }

    public String getParentTopicId() {
        return parentTopicId;
    }

    public void setParentTopicId(String parentTopicId) {
        this.parentTopicId = parentTopicId;
    }
}
