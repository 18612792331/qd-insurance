package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;



import lombok.Data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

@Data
public class PolicyListResponseData extends ResponseData{

	
	private static final long serialVersionUID = -6717929639702567084L;
	
	@ExplainAnnotation (explain = "单证ID")
	private String policyId;
	
	@ExplainAnnotation (explain = "PICC订单号")
	private String policyNo;
	
	@ExplainAnnotation (explain = "保单状态")
	private String status;
	
	@ExplainAnnotation (explain = "商品名称")
	private String wareName;
	
	@ExplainAnnotation (explain = "被保险人名称")
	private String insurantName;
	
	@ExplainAnnotation (explain = "被保险财产（房屋）地址")
	private String roomAddress;
	
	@ExplainAnnotation (explain = "生效日期")
	private String effectTime;
	
	@ExplainAnnotation (explain = "到期日期")
	private String expireTime;
}
