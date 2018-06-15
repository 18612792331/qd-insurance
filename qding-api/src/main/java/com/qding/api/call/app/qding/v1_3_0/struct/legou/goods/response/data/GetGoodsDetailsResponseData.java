package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.response.data;

import com.qding.api.struct.ResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.GoodsDetail;

/**
 * 获取商品详情
 * @author jiawenzheng
 *
 */
public class GetGoodsDetailsResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2389824629697683687L;

    /**
     * 商品详情信息对象列表
     */
	private GoodsDetail entity;


	
	public GoodsDetail getEntity() {
		return entity;
	}



	public void setEntity(GoodsDetail entity) {
		this.entity = entity;
	}

	
	public GetGoodsDetailsResponseData (){
		
	}


	@Override
	public String toString() {
		return "GetGoodsDetailsResponseData [entity=" + entity
				+ ", toString()="+ super.toString() + "]";
	}

	
}
