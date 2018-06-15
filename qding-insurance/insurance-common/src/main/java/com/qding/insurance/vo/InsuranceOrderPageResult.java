package com.qding.insurance.vo;

import java.io.Serializable;
import java.util.Date;

import com.qding.insurance.domain.InsuranceOrder;
import com.qding.insurance.domain.InsurancePolicy;

public class InsuranceOrderPageResult extends InsuranceOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8437924906371427262L;
	/**
	 * 品类
	 */
	private Long categoryId;
	
	/**
	 * 供方
	 */
	private String providerName;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
}
