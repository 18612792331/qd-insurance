package com.qding.api.call.app.qding.v4_0_0.struct.stage;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wang.cheng.liang time 2018.5.8
 */
public class ParcelInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1442030564008552614L;

	@Getter
	@Setter
	@ExplainAnnotation(explain = "包裹号")
	private String id;

	@Getter
	@Setter
	@ExplainAnnotation(explain = "包裹状态")
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
	@ExplainAnnotation(explain = "入库时间")
	private String putTime;

}
