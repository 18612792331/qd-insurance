package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/3/3.
 */
public class RecommendEncyclopediaDTO extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -7086892326328364732L;

    @ExplainAnnotation(explain = "图片列表")
    private List<String> topicImage;

    @ExplainAnnotation(explain = "标题")
    private String topicTitle;

    @ExplainAnnotation(explain = "百科分类名称")
    private String typeName;

    @ExplainAnnotation(explain = "发布时间")
    private Long publishTime;


    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

	public List<String> getTopicImage() {
		return topicImage;
	}

	public void setTopicImage(List<String> topicImage) {
		this.topicImage = topicImage;
	}
    
    
    
    
}
