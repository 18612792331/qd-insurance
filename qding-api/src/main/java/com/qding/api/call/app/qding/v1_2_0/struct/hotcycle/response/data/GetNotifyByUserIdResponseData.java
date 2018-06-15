package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedReplyNotify;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.IllegalNotify;
import com.qding.api.struct.ResponseData;

/**
 * 根据用户id查询新消息（由客户端定时拉取）	
 * @author lichao
 *
 */
public class GetNotifyByUserIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3538817971648238703L;

	/**
	 * 消息总数
	 */
	private int noticeNum;
	
	/**
	 * 信息类评论通知列表
	 */
	private List<FeedReplyNotify> feedReplyNotifies;
	
	/**
	 * 违规类通知列表
	 */
	private List<IllegalNotify> illegalNotifies;
	
	
	public GetNotifyByUserIdResponseData() {

	}

	public GetNotifyByUserIdResponseData(int noticeNum,
			List<FeedReplyNotify> feedReplyNotifies,
			List<IllegalNotify> illegalNotifies) {
		super();
		this.noticeNum = noticeNum;
		this.feedReplyNotifies = feedReplyNotifies;
		this.illegalNotifies = illegalNotifies;
	}

	public int getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}

	public List<FeedReplyNotify> getFeedReplyNotifies() {
		return feedReplyNotifies;
	}

	public void setFeedReplyNotifies(List<FeedReplyNotify> feedReplyNotifies) {
		this.feedReplyNotifies = feedReplyNotifies;
	}

	public List<IllegalNotify> getIllegalNotifies() {
		return illegalNotifies;
	}

	public void setIllegalNotifies(List<IllegalNotify> illegalNotifies) {
		this.illegalNotifies = illegalNotifies;
	}

	@Override
	public String toString() {
		return "GetNotifyByUserIdResponse [noticeNum=" + noticeNum
				+ ", feedReplyNotifies=" + feedReplyNotifies
				+ ", illegalNotifies=" + illegalNotifies + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
