package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 个人标签页 图片列表
 * @author lichao
 *
 */
public class GetPersonalFeedByTagIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6585186157490744821L;

	private String tagId;
	
	private String userId;
	
	private int pageNo = 1;
	
	private int pageSize = 5;
	
	/**
	 * 显示点赞记录数
	 */
	private int showPraiseSize;
	
	/**
	 * 显示评论记录数
	 */
	private int showCommentSize;
	
	
	public GetPersonalFeedByTagIdRequest() {

	}


	public GetPersonalFeedByTagIdRequest(String tagId, String userId,
			int pageNo, int pageSize, int showPraiseSize, int showCommentSize) {
		super();
		this.tagId = tagId;
		this.userId = userId;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.showPraiseSize = showPraiseSize;
		this.showCommentSize = showCommentSize;
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



	public String getTagId() {
		return tagId;
	}


	public void setTagId(String tagId) {
		this.tagId = tagId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
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



	@Override
	public String toString() {
		return "GetPersonalFeedByTagIdRequest [tagId=" + tagId + ", userId="
				+ userId + ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", showPraiseSize=" + showPraiseSize + ", showCommentSize="
				+ showCommentSize + ", toString()=" + super.toString() + "]";
	}


}
