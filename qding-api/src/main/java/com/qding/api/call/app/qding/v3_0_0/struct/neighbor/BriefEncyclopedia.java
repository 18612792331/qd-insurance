package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/3/4.
 */
public class BriefEncyclopedia extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -5015661814541731146L;

    @ExplainAnnotation(explain = "帖子ID")
    private String id;

    @ExplainAnnotation(explain = "百科标题",desc = "即帖子topicTitle")
    private String title;

    @ExplainAnnotation(explain = "百科类型名称Id")
    private String typeNameId;
    
    @ExplainAnnotation(explain = "百科类型名称")
    private String typeName;

    @ExplainAnnotation (explain = "百科导语")
    private String introduction;

    @ExplainAnnotation (explain = "百科关键词")
    private String keywords;

    @ExplainAnnotation (explain = "百科作者")
    private String author;

    @ExplainAnnotation (explain = "百科头图")
    private List<String> imgUrl;

    @ExplainAnnotation (explain = "发布时间")
    private Long publishTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

	public String getTypeNameId() {
		return typeNameId;
	}

	public void setTypeNameId(String typeNameId) {
		this.typeNameId = typeNameId;
	}
    
    
    
    
}
