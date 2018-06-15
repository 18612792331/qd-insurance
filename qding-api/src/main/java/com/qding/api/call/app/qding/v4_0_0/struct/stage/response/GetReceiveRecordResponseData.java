package com.qding.api.call.app.qding.v4_0_0.struct.stage.response;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.ParcelInfoDTO;
import com.qding.api.struct.ResponseData;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wang.cheng.liang
 * time 2018.5.8
 */
public class GetReceiveRecordResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5131948050095945275L;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="包裹信息列表")
	private List<ParcelInfoDTO> parcels;
	
}
