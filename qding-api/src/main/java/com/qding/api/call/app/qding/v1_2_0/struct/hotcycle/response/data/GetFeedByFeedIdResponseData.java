package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed;
import com.qding.api.struct.ResponseData;
import com.qding.framework.common.smart.validate.Validate;

/**
 * 根据图文消息id获取图文消息详情				
 * @author lichao
 *
 */
@Validate
public class GetFeedByFeedIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3310472948960921454L;

	private Feed entity;


	
	public GetFeedByFeedIdResponseData() {

	}
	
	public GetFeedByFeedIdResponseData(Feed feed) {
		super();
		this.entity = feed;
	}



	@Override
	public String toString() {
		return "GetFeedByFeedIdResponse [entity=" + entity + " toString()="
				+ super.toString() + "]";
	}

	public void setEntity(Feed entity) {
		this.entity = entity;
	}
	
	public Feed getEntity() {
		return entity;
	}
}
