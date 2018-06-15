package com.qding.api.call.app.qding.v4_0_0.struct.stage.response;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.stage.dto.LogisticsDto;
import com.qding.stage.dto.Mailing;

/**
 * 
 * @author wang.cheng.liang 
 * time 2018.05.11
 *
 */
@Validate
public class GetAbleExpressInfoResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1443141304211305466L;
	
	@Setter
	@Getter
	@ExplainAnnotation(explain="物流公司信息")
	@NotNullValidate
	private List<Map<String, String>> logisticsList;
	
	@Setter
	@Getter
	@ExplainAnnotation(explain="托寄物类型信息")
	@NotNullValidate
	private List<Map<String, String>> goodsTypeList;
	
	
	
	

}
