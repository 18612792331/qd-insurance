package com.qding.api.call.app.qding.v3_2_0.struct.coupon.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/3.
 */
public class GetReportDataResponseData extends ResponseData {

	private static final long serialVersionUID = -3048088629820103021L;
	
	@ExplainAnnotation (explain = "未用总数")
	private Integer unusedTotal;
	
	@ExplainAnnotation (explain = "已用总数")
	private Integer usedTotal;
	
	@ExplainAnnotation (explain = "过期总数")
	private Integer overDueTotal;

	public Integer getUnusedTotal() {
		return unusedTotal;
	}

	public void setUnusedTotal(Integer unusedTotal) {
		this.unusedTotal = unusedTotal;
	}

	public Integer getUsedTotal() {
		return usedTotal;
	}

	public void setUsedTotal(Integer usedTotal) {
		this.usedTotal = usedTotal;
	}

	public Integer getOverDueTotal() {
		return overDueTotal;
	}

	public void setOverDueTotal(Integer overDueTotal) {
		this.overDueTotal = overDueTotal;
	}
	
	
	
	
     
}
