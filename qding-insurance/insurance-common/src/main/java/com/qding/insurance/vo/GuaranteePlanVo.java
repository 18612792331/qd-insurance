package com.qding.insurance.vo;

import java.util.List;

import com.qding.insurance.domain.GuaranteeItem;
import com.qding.insurance.domain.GuaranteeObject;
import com.qding.insurance.domain.GuaranteePlan;

public class GuaranteePlanVo extends GuaranteePlan {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GuaranteeItemVo itemVo;

	public GuaranteeItemVo getItemVo() {
		return itemVo;
	}

	public void setItemVo(GuaranteeItemVo itemVo) {
		this.itemVo = itemVo;
	}



}
