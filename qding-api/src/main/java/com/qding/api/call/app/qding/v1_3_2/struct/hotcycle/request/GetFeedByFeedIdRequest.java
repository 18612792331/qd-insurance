package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据图文消息id获取图文消息详情				
 * @author lichao
 *
 */
public class GetFeedByFeedIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2026721593062602546L;
 
	/**
	 * 信息Id
	 */
	private String feedId;
	
	/**
	 * 显示点赞记录数
	 */
	private int showPraiseSize;
	
	/**
	 * 显示评论记录数
	 */
	private int showCommentSize;

	public GetFeedByFeedIdRequest(String feedId, int showPraiseSize,
								  int showCommentSize) {
		super();
		this.feedId = feedId;
		this.showPraiseSize = showPraiseSize;
		this.showCommentSize = showCommentSize;
	}

	public GetFeedByFeedIdRequest() {

	}

	@Override
	public String toString() {
		return "GetFeedByFeedIdRequest [feedId=" + feedId + ", showPraiseSize="
				+ showPraiseSize + ", showCommentSize=" + showCommentSize
				+ ", toString()=" + super.toString() + "]";
	}

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
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
