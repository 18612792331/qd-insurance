package com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.popularize.PopularizeQuickMarkDto;
import com.qding.api.struct.ResponseData;

/**
 * Created by Administrator on 2015/7/28.
 */
public class GetPopularizeQuickMarkResponseData   extends ResponseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1097035006262805406L;
	
	private PopularizeQuickMarkDto entity;

	public PopularizeQuickMarkDto getEntity() {
		return entity;
	}

	public void setEntity(PopularizeQuickMarkDto entity) {
		this.entity = entity;
	}

	@Override
    public String toString() {
        return "GetPopularizeQuickMarkResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }

}
