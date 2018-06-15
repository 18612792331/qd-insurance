package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.Notice;
import com.qding.api.struct.ResponseData;

public class GetNoticeByIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1979453072730345513L;
 
	private Notice entity;
	
	public GetNoticeByIdResponseData() {

	}

	public GetNoticeByIdResponseData(Notice entity) {
		super();
		this.entity = entity;
	}

	public Notice getEntity() {
		return entity;
	}

	public void setEntity(Notice entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "GetBannerByIdResponseData [entity=" + entity + ", toString()="
				+ super.toString() + "]";
	}
	
}
