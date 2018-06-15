package com.qding.api.call.app.qding.v4_1_0.struct.insurance.request;

import lombok.Data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
@Data
public class ApplyCompensateRequest extends BaseRequest{
	private static final long serialVersionUID = 2405304250922283584L;

	@ExplainAnnotation (explain = "保单ID")
	private String policyId;
	
	@ExplainAnnotation (explain = "保障内容ID")
	private String guaranteeItemId;
	
	@ExplainAnnotation (explain = "联系人姓名")
	private String contactName;
	
	@ExplainAnnotation (explain = "联系人电话")
	private String contactPhone;
	
	@ExplainAnnotation (explain = "出险时间, 格式 yyyy-MM-dd HH:mm:ss")
	private String happenTime;
	
	@ExplainAnnotation (explain = "估损金额")
	private String estimateMoney;
	
	@ExplainAnnotation (explain = "事故描述")
	private String accidentMemo;
}
