package com.qding.api.call.app.qding.v4_0_0.struct.stage.response;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.logisticsDTO;
import com.qding.api.struct.ResponseData;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author wang.cheng.liang
 *	create time 2018.5.8
 */
public class GetCommunityExpressResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3343688362516888695L;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="社区状态")
	private int communityStatus;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="社区可用物流")
	private List<logisticsDTO> logistics;
	
	

}
