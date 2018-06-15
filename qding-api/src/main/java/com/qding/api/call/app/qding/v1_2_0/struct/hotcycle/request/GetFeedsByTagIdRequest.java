package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据标签id获取此标签的图片列表，按发布时间排序，带分页					
 * @author lichao
 *
 */
public class GetFeedsByTagIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6109495249489980970L;

	
	/**
	 * 标签ID
	 */
	private String tagId;
	
	/**
	 * 当前页
	 */
	private int pageNo = 1;
	
	/**
	 * 页大小
	 */
	private int pageSize = 5;
	
	/**
	 * 显示点赞记录数
	 */
	private int showPraiseSize;
	
	/**
	 * 显示评论记录数
	 */
	private int showCommentSize;
	
	
	public GetFeedsByTagIdRequest() {
	}


	public GetFeedsByTagIdRequest(String tagId, int pageNo, int pageSize,
			int showPraiseSize, int showCommentSize) {
		super();
		this.tagId = tagId;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.showPraiseSize = showPraiseSize;
		this.showCommentSize = showCommentSize;
	}


	@Override
	public String toString() {
		return "GetFeedsByTagIdRequest [tagId=" + tagId + ", pageNo=" + pageNo
				+ ", pageSize=" + pageSize + ", showPraiseSize="
				+ showPraiseSize + ", showCommentSize=" + showCommentSize
				+ ", toString()=" + super.toString() + "]";
	}


	public String getTagId() {
		return tagId;
	}


	public void setTagId(String tagId) {
		this.tagId = tagId;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getShowPraiseSize() {
		return showPraiseSize;
	}


	public void setShowPraiseSize(int showPraiseSize) {
		this.showPraiseSize = showPraiseSize;
	}


	public int getShowCommentSize() {
		return showCommentSize;
	}


	public void setShowCommentSize(int showCommentSize) {
		this.showCommentSize = showCommentSize;
	}

	
}
