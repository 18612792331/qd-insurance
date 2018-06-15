package com.qding.api.call.app.qding.v4_0_0.struct.stage.response;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.logistics.platform.client.remote.struct.bean.LogisticsLogDto;

/**
 * @author wang.cheng.liang
 * time 2018.5.8
 */
public class GetSendDetailsResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7007626858556664263L;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="寄件号")
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
	private String sendAddrId;
	
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
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="创建时间")
	private Date createTime;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="物流信息")
	private List<LogisticsLogDto> logisticsInfo;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="快递信息状态  寄件状态分别为1 未揽件, 2 已揽件,3 已邮寄, 4 已取消")
	private Integer status;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain = "快递单号")
	private String logisticsCode;
	
}
