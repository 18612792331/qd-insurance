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
 *	create time 2018.5.8
 */
@Validate
public class CommitSendRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 501946969336936566L;
	
	@Setter
    @Getter
    @ExplainAnnotation (explain = "寄件人姓名")
	@NotNullValidate
	private String sendName;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "寄件人手机")
	@NotNullValidate
	private String sendPhone;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "寄件人省份ID")
	@NotNullValidate
	private String sendProvinceId;
	
	@Setter
	@Getter
	@ExplainAnnotation(explain = "寄件人省份")
	@NotNullValidate
	private String sendProvinceName;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "寄件人市ID")
	@NotNullValidate
	private String sendRegionId;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "寄件人市")
	@NotNullValidate
	private String sendRegionName;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "寄件人区ID")
	@NotNullValidate
	private String sendDistinctId;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "寄件人区")
	@NotNullValidate
	private String sendDistinctName;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "寄件人详细地址")
	@NotNullValidate
	private String sendAddr;
	
	@Setter
	@Getter
	@ExplainAnnotation (explain="寄件人详细信息地址ID")
	@NotNullValidate
	private String sendAddrId;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "收件人姓名")
	@NotNullValidate
	private String receiverName;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "收件人手机号")
	@NotNullValidate
	private String receiverPhone;

	@Setter
    @Getter	
	@ExplainAnnotation (explain = "收件人省份ID")
	@NotNullValidate
	private String receiverProvinceId;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "收件人省份")
	@NotNullValidate
	private String receiverProvinceName;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "收件人市ID")
	@NotNullValidate
	private String receiverRegionId;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "收件人市")
	@NotNullValidate
	private String receiverRegionName;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "收件人区ID")
	@NotNullValidate
	private String receiverDistinctId;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "收件人区")
	@NotNullValidate
	private String receiverDistinctName;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "收件人详细地址")
	@NotNullValidate
	private String receiverAddr;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "快递公司ID")
	@NotNullValidate
	private Long logisticsId;
	
	@Setter
	@Getter	
	@ExplainAnnotation (explain = "快递公司")
	@NotNullValidate
	private String logisticsName;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "寄件物品名称")
	@NotNullValidate
	private String goodsName;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "寄件物品重量")
	private Float weight;
	
	@Setter
	@Getter
	@ExplainAnnotation(explain="社区ID")
	@NotNullValidate
	private String ProjectId;

	@Setter
	@Getter
	@ExplainAnnotation(explain="社区名称")
	@NotNullValidate
	private String projectName;
	
	@Setter
	@Getter
	@ExplainAnnotation(explain="第三方物流id")
	@NotNullValidate
	private String thirdId;
}
