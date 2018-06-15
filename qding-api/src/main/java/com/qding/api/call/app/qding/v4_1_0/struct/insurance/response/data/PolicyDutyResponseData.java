package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import lombok.Data;

import com.qding.api.struct.ResponseData;
@Data
public class PolicyDutyResponseData extends ResponseData{

	private static final long serialVersionUID = -1202213543646255695L;
	// 保障内容ID
	private String guaranteeItemId;
	
	// 保障内容标题
	private String guaranteeItemTitle;
	
	// 应有权益
	private String rightValue;
	
	// 剩余权益
	private String balanceRightValue;
	

	//保障对象属性：1：商品，2：外部理赔
	private String itemType;

	//理赔方式，1：按金额，2：按次数（次）
	private String compensateType;

	
}
