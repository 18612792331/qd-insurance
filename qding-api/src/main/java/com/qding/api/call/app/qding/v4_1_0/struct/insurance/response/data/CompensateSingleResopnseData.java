package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import lombok.Data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;
@Data
public class CompensateSingleResopnseData extends ResponseData{
	
	private static final long serialVersionUID = 7287404602002141986L;
	@ExplainAnnotation (explain = "理赔ID")
	private String compensateId;
	
	@ExplainAnnotation (explain = "单证ID")
	private String policyId;
	
	@ExplainAnnotation (explain = "picc保单号")
	private String policyIdNo;
	
	@ExplainAnnotation (explain = "被保险人名称")
	private String insurantName;
		
	@ExplainAnnotation (explain = "商品名称")
	private String wareName;
	//1：已锁定，2：已完成，3：已撤销
	@ExplainAnnotation (explain = "理赔状态")
	private String compensateStatus;
	
	@ExplainAnnotation (explain = "理赔金额/次数")
	private String compensateAmount;
	
	@ExplainAnnotation (explain = "申请时间")
	private String createTime;
	
	@ExplainAnnotation (explain = "完成时间")
	private String finishTime;
	
	@ExplainAnnotation (explain = "保险财产地址")
	private String roomAddress;
	
	
	
}
