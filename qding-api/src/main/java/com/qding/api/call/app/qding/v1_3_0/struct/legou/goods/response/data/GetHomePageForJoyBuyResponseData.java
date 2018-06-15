package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.response.data;

import com.qding.api.struct.ResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.HomePageEntity;

/**
 * 获取乐购首页信息
 * @author jiawenzheng
 *
 */
public class GetHomePageForJoyBuyResponseData extends ResponseData{

	
	private static final long serialVersionUID = 5129728630501805017L;
	
	/**
	 * 乐购首页信息对象
	 */
	public HomePageEntity entity;
	
	 
	public HomePageEntity getEntity() {
		return entity;
	}


	public void setEntity(HomePageEntity entity) {
		this.entity = entity;
	}


	public GetHomePageForJoyBuyResponseData(HomePageEntity entity) {
		super();
		this.entity = entity;
	}

	public GetHomePageForJoyBuyResponseData(){
		
	}

	@Override
	public String toString() {
		return "GetHomePageForJoyBuyResponseData [ entity=" + entity
				+ ", toString()="+ super.toString() + "]";
	}
	
}
