package com.qding.api.call.app.qding.v4_0_0.struct.chatRecord;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;

public class LinkDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3886641745236490626L;

	/**
     * 跳转的标题
     */
	@ExplainAnnotation (explain = "标题")
    private String title;

    /**
     * 跳转的链接或skipMode,由type决定
     */
	@ExplainAnnotation (explain = "跳转的链接或skipMode")
    private String value;

    /**
     * 跳转类型
     */
	@ExplainAnnotation (explain = "跳转类型", desc = "1:url,2:skipMode,3:回传为问题")
    private Integer type;

    /**
     * 知识ID
     */
	@ExplainAnnotation (explain = "知识ID")
    private String knowledgeId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	@Override
	public String toString() {
		return "LinkDto [title=" + title + ", value=" + value + ", type="
				+ type + ", knowledgeId=" + knowledgeId + "]";
	}

}
