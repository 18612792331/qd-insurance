package com.qding.api.call.app.qding.v4_0_0.struct.stage.response;

import java.util.Date;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;
import com.qding.logistics.platform.client.remote.struct.bean.LogisticsLogDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wang.cheng.liang
 * time 2018.5.8
 */
public class GetReceiveDetailsResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7007626858556664263L;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="包裹号")
	private String parcelId;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="入库时间")
	private String putTime;

	@Getter
	@Setter
	@ExplainAnnotation(explain="创建时间")
	private Date createTime;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="寄件人姓名")
	private String userName;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="寄件人电话")
	private String phone;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="快递公司名称")
	private String logisticsName;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="快递运单号")
	private String logisticsCode;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="物流信息")
	private List<LogisticsLogDto> logisticsInfo;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain=" 状态 未入库，未区间 已取件 问题件 退回件 派送件")
	private Integer status;
	
}
