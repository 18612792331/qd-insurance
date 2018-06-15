package com.qding.insurance.vo;

import java.io.Serializable;

import com.qding.insurance.domain.CompensateRecord;

public class CompensateRecordPageResult extends CompensateRecord implements Serializable {

	
	private static final long serialVersionUID = -3632560908357361960L;
	/**
	 * 申请类型
	 */
	private String applyType;

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	

	


}
