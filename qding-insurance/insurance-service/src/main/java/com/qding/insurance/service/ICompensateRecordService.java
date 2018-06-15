package com.qding.insurance.service;

import java.util.List;

import com.qding.framework.common.basemodel.ResultPage;
import com.qding.insurance.domain.CompensateRecord;
import com.qding.insurance.param.CompensateRecordParam;
import com.qding.insurance.vo.CompensateRecordPageResult;
import com.qding.insurance.vo.CompensateRecordResult;
import com.qding.insurance.vo.CompensateRecordVo;

public interface ICompensateRecordService {
    
    void updateCompensate(CompensateRecord compensate);

    void addCompensate(CompensateRecord compensate);
	
	/**
	 * 单证理赔记录
	 */
	List<CompensateRecordResult> getPolicyCompensate(String policyId, String itemId);

	/**
	 * 查询理赔记录
	 */
	ResultPage<CompensateRecordPageResult> getResultPage(CompensateRecordParam param);
	/**
	 * 根据ID查询理赔记录
	 */
	CompensateRecord findById(String id);
	
	/**
	 * 根据单证ID获取用户保单理赔列表
	 * @param policyID
	 * @param status
	 * @return
	 */
	List<CompensateRecordVo> getCompensateRecordListByPolicyIDandStatus(
			String policyID, Integer status);
	
	/**
	 * 查询平台订单对应的理赔记录
	 */
	CompensateRecord getByOrderCode(String roomId, String orderCode);
	
	/**
	 * PICC授权通过
	 */
	void piccAuth(String id)  throws Exception;

	/**
	 * 理赔管理页面-根据ID查询
	 * @return
	 */
	CompensateRecordVo getComRecordVoById(String id);
    

}
  
