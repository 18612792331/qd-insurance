package com.qding.insurance.vo;

import java.io.Serializable;
import java.util.List;

import com.qding.insurance.domain.GuaranteePlan;
import com.qding.insurance.domain.InsuranceSku;

public class GuaranteePlanResultVo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private InsuranceSku sku;
	
	private List<GuaranteePlanVo> planVoList;
	
	public InsuranceSku getSku() {
		return sku;
	}

	public void setSku(InsuranceSku sku) {
		this.sku = sku;
	}

	public List<GuaranteePlanVo> getPlanVoList() {
		return planVoList;
	}

	public void setPlanVoList(List<GuaranteePlanVo> planVoList) {
		this.planVoList = planVoList;
	}

}
