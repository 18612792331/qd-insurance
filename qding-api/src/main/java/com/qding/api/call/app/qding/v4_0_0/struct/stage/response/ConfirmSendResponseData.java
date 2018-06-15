package com.qding.api.call.app.qding.v4_0_0.struct.stage.response;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author wang.cheng.liang
 *	create time 2018.5.8
 */
public class ConfirmSendResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7862408853515164807L;
	
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="应付金额")
	private String cash;

	@Getter
	@Setter
	@ExplainAnnotation(explain="实付金额")
	private String realCash;
	
	@Getter
	@Setter
    @ExplainAnnotation (explain = "寄件人姓名")
	private String sendName;
	
	@Getter
	@Setter
    @ExplainAnnotation (explain = "寄件人手机")
	private String sendPhone;
	
	@Getter
	@Setter
    @ExplainAnnotation (explain = "寄件人省份")
	private String sendProvinceName;
	
	@Getter
	@Setter
	@ExplainAnnotation (explain = "寄件人市")
	private String sendRegionName;
	
	@Getter
	@Setter
	@ExplainAnnotation (explain = "寄件人地区")
	private String sendDistinctName;
	
	@Getter
	@Setter
    @ExplainAnnotation (explain = "寄件人详细地址")
	private String sendAddr;
	
	@Getter
	@Setter
    @ExplainAnnotation (explain = "收件人姓名")
	private int receiverName;
	
	@Getter
	@Setter
    @ExplainAnnotation (explain = "收件人手机号")
	private int receiverPhone;

	@Getter
	@Setter
    @ExplainAnnotation (explain = "收件人省份")
	private String receiverProvinceName;
	
	@Getter
	@Setter
	@ExplainAnnotation (explain = "收件人市")
	private String receiverRegionName;
	
	@Getter
	@Setter
	@ExplainAnnotation (explain = "收件人地区")
	private String receiverDistinctName;
	
	@Getter
	@Setter
    @ExplainAnnotation (explain = "收件人详细地址")
	private String receiverAddr;
	
}
