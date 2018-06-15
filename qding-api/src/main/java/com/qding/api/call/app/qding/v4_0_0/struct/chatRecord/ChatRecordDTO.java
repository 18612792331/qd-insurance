package com.qding.api.call.app.qding.v4_0_0.struct.chatRecord;

import java.io.Serializable;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;

public class ChatRecordDTO implements Serializable {

	private static final long serialVersionUID = 4582828939712057285L;

	/**
	 * 会话ID
	 */
	@ExplainAnnotation (explain = "会话ID")
	private String recordGroupId;

	/**
	 * 内容ID
	 */
	@ExplainAnnotation (explain = "聊天记录ID")
	private String recordId;

	/**
	 * 内容类型
	 */
	@ExplainAnnotation (explain = "聊天记录类型", desc = "1:问题,2:应答")
	private Integer recordType;

	/**
	 * 文本内容
	 */
	@ExplainAnnotation (explain = "聊天记录内容")
	private String recordContent;

	/**
     * 知识ID
     */
	@ExplainAnnotation (explain = "知识ID")
    private String knowledgeId;

    /**
     * 反馈结果
     */
	@ExplainAnnotation (explain = "反馈结果", desc = "1:点赞,2:点踩")
    private String feedbackResult;


    /**
     * 跳转信息
     */
	@ExplainAnnotation (explain = "跳转信息")
    private List<LinkDTO> linkDtoList;

	/**
	 * 创建时间
	 */
	@ExplainAnnotation (explain = "创建时间")
	private Long createTime;

	public String getRecordGroupId() {
		return recordGroupId;
	}

	public void setRecordGroupId(String recordGroupId) {
		this.recordGroupId = recordGroupId;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public String getRecordContent() {
		return recordContent;
	}

	public void setRecordContent(String recordContent) {
		this.recordContent = recordContent;
	}

	
	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getFeedbackResult() {
		return feedbackResult;
	}

	public void setFeedbackResult(String feedbackResult) {
		this.feedbackResult = feedbackResult;
	}

	public List<LinkDTO> getLinkDtoList() {
		return linkDtoList;
	}

	public void setLinkDtoList(List<LinkDTO> linkDtoList) {
		this.linkDtoList = linkDtoList;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ChatRecordDTO [recordGroupId=" + recordGroupId + ", recordId="
				+ recordId + ", recordType=" + recordType + ", recordContent="
				+ recordContent + ", knowledgeId=" + knowledgeId
				+ ", feedbackResult=" + feedbackResult + ", linkDtoList="
				+ linkDtoList + ", createTime=" + createTime + "]";
	}

	
}
