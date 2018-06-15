package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据社区id返回所属商圈的图文信息，带分页				
 * @author lichao
 *
 */
public class GetFeedByCommunityIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1620318114198530283L;

	public GetFeedByCommunityIdRequest(String communityId, int pageNo,
			int pageSize, int showPraiseSize, int showCommentSize) {
		super();
		this.communityId = communityId;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.showPraiseSize = showPraiseSize;
		this.showCommentSize = showCommentSize;
	}

	/**
	 * 社区ID
	 */
	private String communityId;
	
	/**
	 * 当前页
	 */
	private int pageNo = 1;
	
	/**
	 * 信息页大小
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
	
	public GetFeedByCommunityIdRequest() {

	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
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

	@Override
	public String toString() {
		return "GetFeedByCommunityIdRequest [communityId=" + communityId
				+ ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", showPraiseSize=" + showPraiseSize + ", showCommentSize="
				+ showCommentSize + ", toString()=" + super.toString() + "]";
	}
}
