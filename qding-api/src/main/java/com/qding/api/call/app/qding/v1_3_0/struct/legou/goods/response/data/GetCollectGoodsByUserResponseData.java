package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.response.data;

import java.util.List;

import com.qding.api.struct.ResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;

public class GetCollectGoodsByUserResponseData extends ResponseData{

	
	private static final long serialVersionUID = -704498277201470917L;

	/**
	 * 当前查询记录数
	 */
	private int recordCount;
	
	/**
	 * 总记录数
	 */
	private int totalCount;
	
	/**
	 * 商品信息列表
	 */
	private List<Goods> list;

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Goods> getList() {
		return list;
	}

	public void setList(List<Goods> list) {
		this.list = list;
	}

	public GetCollectGoodsByUserResponseData(int recordCount, int totalCount,
			List<Goods> list) {
		super();
		this.recordCount = recordCount;
		this.totalCount = totalCount;
		this.list = list;
	}
	
	public GetCollectGoodsByUserResponseData(){
		
	}
	
	@Override
	public String toString() {
		return "GetCollectGoodsByUserResponseData [recordCount=" + recordCount
				+ ", totalCount=" + totalCount + ", list=" + list
				+ ", toString()="+ super.toString() + "]";
	}
}
