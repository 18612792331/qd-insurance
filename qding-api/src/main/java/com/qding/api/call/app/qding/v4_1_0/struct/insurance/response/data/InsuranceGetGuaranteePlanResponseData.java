package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;
import com.qding.insurance.vo.GuaranteeItemVo;
import com.qding.insurance.vo.GuaranteePlanResultVo;

/**
 * Created by zhangxiaojun on 2018/6/7.
 */
public class InsuranceGetGuaranteePlanResponseData extends ResponseData {
	private static final long serialVersionUID = -4500104133786163404L;
	
	@ExplainAnnotation(explain = "保障计划相关信息")
	private GuaranteePlanResultVo resultVo;

	public GuaranteePlanResultVo getResultVo() {
		return resultVo;
	}

	public void setResultVo(GuaranteePlanResultVo resultVo) {
		this.resultVo = resultVo;
	}

	
}
