package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedPraise;
import com.qding.api.struct.ResponseData;

/**
 * 获取信息的赞列表
 * @author lichao
 *
 */
public class GetPraiseByFeedIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3020078424099642896L;

	/**
	 * 总记录数
	 */
	private int totalCount;
	
	/**
	 * 当前页返回记录数
	 */
	private int recordCount;
	
	
	/**
	 * 点赞列表
	 */
	private List<FeedPraise> list;
	
	public GetPraiseByFeedIdResponseData() {

	}

	public GetPraiseByFeedIdResponseData(int totalCount, int recordCount,
			List<FeedPraise> list) {
		super();
		this.totalCount = totalCount;
		this.recordCount = recordCount;
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<FeedPraise> getList() {
		return list;
	}
	
	public void setList(List<FeedPraise> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetPraiseByFeedIdResponse [totalCount=" + totalCount
				+ ", recordCount=" + recordCount + ", list="
				+ list + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
