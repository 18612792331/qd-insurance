package com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.popularize.PopularizeApplyStatusDto;
import com.qding.api.struct.ResponseData;

/**
 * Created by Administrator on 2015/7/28.
 */
public class GetPopularizeApplyStatusResponseData   extends ResponseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6887466790362199284L;
	

	private PopularizeApplyStatusDto entity;

	public PopularizeApplyStatusDto getEntity() {
		return entity;
	}

	public void setEntity(PopularizeApplyStatusDto entity) {
		this.entity = entity;
	}

	@Override
    public String toString() {
        return "GetPopularizeApplyStatusResponseData [entity="+entity+" ,toString()=" + super.toString() + "]";
    }
}
