package com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.popularize.PopularizeStatistics;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.PopularizeUserPassDto;
import com.qding.api.struct.ResponseData;

/**
 * Created by Administrator on 2015/7/28.
 */
public class GetPopularizeQuickMarkInfoResponseData   extends ResponseData {


	private static final long serialVersionUID = 1045409253040160897L;
	
	private PopularizeUserPassDto entity;

	private PopularizeStatistics statistics;

	public PopularizeUserPassDto getEntity() {
		return entity;
	}

	public void setEntity(PopularizeUserPassDto entity) {
		this.entity = entity;
	}

	public PopularizeStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(PopularizeStatistics statistics) {
		this.statistics = statistics;
	}

	public GetPopularizeQuickMarkInfoResponseData(){
		
	}
	
	
}
