package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;
import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;
import com.qding.insurance.vo.GuaranteeItemVo;
import com.qding.insurance.vo.GuaranteePlanResultVo;

/**
 * Created by zhangxiaojun on 2018/6/11.
 */
public class InsuranceWareListResponseData extends ResponseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<InsuranceWareExtWithBLOBs> list;

	public List<InsuranceWareExtWithBLOBs> getList() {
		return list;
	}

	public void setList(List<InsuranceWareExtWithBLOBs> list) {
		this.list = list;
	}

}
