package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 评论请求
 */
public class CommentListRequest extends BaseRequest {

	private String topicId;

	//每页显示数
	private Integer pageSize;

	//游标时间
	private Long cursorTime;

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getCursorTime() {
		return cursorTime;
	}

	public void setCursorTime(Long cursorTime) {
		this.cursorTime = cursorTime;
	}

	
	

}
