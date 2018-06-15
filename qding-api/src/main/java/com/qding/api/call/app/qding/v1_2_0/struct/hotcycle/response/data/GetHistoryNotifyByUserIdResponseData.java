package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedReplyNotify;
import com.qding.api.struct.ResponseData;

/**
 * 根据用户id查询历史消息
 * @author lichao
 *
 */
public class GetHistoryNotifyByUserIdResponseData extends ResponseData{

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
	private List<FeedReplyNotify> list;
	
	
	public GetHistoryNotifyByUserIdResponseData() {

	}

	public GetHistoryNotifyByUserIdResponseData(int noticeNum,
			List<FeedReplyNotify> list) {
		super();
		this.noticeNum = noticeNum;
		this.list = list;
	}

	public int getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}

	public List<FeedReplyNotify> getList() {
		return list;
	}

	public void setList(List<FeedReplyNotify> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetHistoryNotifyByUserIdResponseData [noticeNum=" + noticeNum
				+ ", list=" + list + ", toString()=" + super.toString() + "]";
	}
	
	
}
