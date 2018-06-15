package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.response.data;

import java.util.List;

import com.qding.api.struct.ResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;

/**
 * 通过商品ID数组获取商品列表
 * @author jiawenzheng 
 *
 */
public class GetGoodsByGoodIdArrayResponseData extends ResponseData{

	
	private static final long serialVersionUID = 2665875746292200061L;
	
	/**
	 * 商品信息列表
	 */
	private List<Goods> list;

	public List<Goods> getList() {
		return list;
	}



	public void setList(List<Goods> list) {
		this.list = list;
	}



	public GetGoodsByGoodIdArrayResponseData(List<Goods> list) {
		super();
		this.list = list;
	}
	
	public GetGoodsByGoodIdArrayResponseData(){
		
	}
	
	@Override
	public String toString() {
		return "GetGoodsByGoodIdArrayResponseData [list=" + list
				+ ", toString()="+ super.toString() + "]";
	}
	

}
