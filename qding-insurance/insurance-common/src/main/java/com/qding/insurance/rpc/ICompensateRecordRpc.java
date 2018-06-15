package com.qding.insurance.rpc;

import com.qding.insurance.rpc.response.CompensateRecordListResponse;
import com.qding.insurance.rpc.response.CompensateRecordResponse;

public interface ICompensateRecordRpc {

	/**
	 * 获取用户保单理赔列表   单证ID（必有），理赔状态（可空）  查询理赔记录
	 */
	public CompensateRecordListResponse getCompensateRecord(String policyID,String status);
	
	/**
	 * 理赔管理页面-查询根据理赔ID
	 * @param id
	 * @return
	 */
	public CompensateRecordResponse getCompensateRecordById(String id);
	
	
	
	

}
