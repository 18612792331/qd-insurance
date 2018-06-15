package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.response.data;

import java.util.List;

import com.qding.api.struct.ResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;

/**
 * 根据分类ID查询商品列表
 * @author jiawenzheng 
 *
 */
public class GetGoodsBySortResponseData  extends ResponseData {

	
	private static final long serialVersionUID = 1308087660961592313L;
	
	   /**
     * 当前记录数
     */
    private int recordCount;
    
    /**
     * 总记录数
     */
    private int totalCount;
    
    /**
     * 商品信息对象列表
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

	public GetGoodsBySortResponseData(int recordCount, int totalCount,
			List<Goods> list) {
		super();
		this.recordCount = recordCount;
		this.totalCount = totalCount;
		this.list = list;
	}
	
	public GetGoodsBySortResponseData(){
		
	}

	@Override
	public String toString() {
		return "GetGoodsBySortResponseData [recordCount=" + recordCount
				+ ", totalCount=" + totalCount + ", list=" + list
				+ ", toString()="+ super.toString() + "]";
	}
	
	


	
	

}
