package com.qding.api.call.app.qding.v4_0_0.struct.stage.request;

import lombok.Getter;
import lombok.Setter;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 
 * @author wang.cheng.liang
 * time 2018.05.14
 *
 */
@Validate
public class ModifyExpressRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2222570863840391930L;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="寄件ID")
	@NotNullValidate
	private String expressId;
	
	@Setter
    @Getter
    @ExplainAnnotation (explain = "寄件人姓名")
	private String sendName;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "寄件人手机")
	private String sendPhone;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "寄件人省份ID")
	private String sendProvinceId;
	
	@Setter
	@Getter
	@ExplainAnnotation(explain = "寄件人省份")
	private String sendProvinceName;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "寄件人市ID")
	private String sendRegionId;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "寄件人市")
	private String sendRegionName;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "寄件人区ID")
	private String sendDistinctId;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "寄件人区")
	private String sendDistinctName;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "寄件人详细地址")
	private String sendAddr;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "收件人姓名")
	private String receiverName;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "收件人手机号")
	private String receiverPhone;

	@Setter
    @Getter	
	@ExplainAnnotation (explain = "收件人省份ID")
	private String receiverProvinceId;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "收件人省份")
	private String receiverProvinceName;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "收件人市ID")
	private String receiverRegionId;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "收件人市")
	private String receiverRegionName;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "收件人区ID")
	private String receiverDistinctId;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "收件人区")
	private String receiverDistinctName;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "收件人详细地址")
	private String receiverAddr;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "快递公司ID")
	private Long logisticsId;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "快递公司")
	private String logisticsName;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "寄件物品名称")
	private String goodsName;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "寄件物品重量")
	private Float weight;
	
	
	
}
