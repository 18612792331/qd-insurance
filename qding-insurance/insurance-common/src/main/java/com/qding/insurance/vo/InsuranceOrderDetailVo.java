package com.qding.insurance.vo;

import java.io.Serializable;
import java.util.Date;

import com.qding.insurance.domain.InsuranceOrder;
import com.qding.insurance.domain.InsurancePolicy;

public class InsuranceOrderDetailVo extends InsuranceOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 投保对象类型
	 */
	private Integer objectType;//投保对象 1：房屋

	public Integer getObjectType() {
		return objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}
	
}
