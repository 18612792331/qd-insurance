package com.qding.api.call.app.qding.v4_0_0.struct.stage;

import java.io.Serializable;
import java.util.Date;

import com.qding.api.annotation.ExplainAnnotation;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wang.cheng.liang time 2018.5.8
 */
public class ExpressInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3690911024629877172L;

	@Getter
	@Setter
	@ExplainAnnotation(explain = "寄件号")
	private String id;

	@Getter
	@Setter
	@ExplainAnnotation(explain = "寄件状态")
	private int status;

	@Getter
	@Setter
	@ExplainAnnotation(explain = "快递公司")
	private String logisticsName;

	@Getter
	@Setter
	@ExplainAnnotation(explain = "快递单号")
	private String logisticsCode;

	@Getter
	@Setter
	@ExplainAnnotation(explain = "下单时间")
	private Date createTime;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain = "订单号")
	private String orderCode;

}
