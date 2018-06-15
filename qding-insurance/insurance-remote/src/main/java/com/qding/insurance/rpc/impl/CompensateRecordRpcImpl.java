package com.qding.insurance.rpc.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import com.qding.framework.common.api.struct.ReturnInfo;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.insurance.rpc.ICompensateRecordRpc;
import com.qding.insurance.rpc.response.CompensateRecordListResponse;
import com.qding.insurance.rpc.response.CompensateRecordResponse;
import com.qding.insurance.service.ICompensateRecordService;
import com.qding.insurance.vo.CompensateRecordVo;
@Service("compensateRecordRpc")
public class CompensateRecordRpcImpl implements ICompensateRecordRpc{
	private static final Logger logger = Logger.getLogger(CompensateRecordRpcImpl.class);
	@Autowired
	private ICompensateRecordService compensateRecordService;

	@Override
	public CompensateRecordListResponse getCompensateRecord(String policyID,String status) {
		CompensateRecordListResponse response = new CompensateRecordListResponse();
		ReturnInfo returnInfo = new ReturnInfo();
		try {
			List<CompensateRecordVo> compensateRecordList = compensateRecordService
					.getCompensateRecordListByPolicyIDandStatus(policyID,transferStatus(status));
			response.setCompensateRecordList(compensateRecordList);
			returnInfo.setCode(HttpStatus.OK.getStatusCode());
			response.setReturnInfo(returnInfo);
			
			if (compensateRecordList == null || compensateRecordList.size() == 0) {
				returnInfo.setMessage("该用户没有理赔信息");
				response.setReturnInfo(returnInfo);
			}
		} catch (Exception e) {
			logger.error("getCompensateRecordByPolicyIDandStatus异常", e);
			returnInfo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
			returnInfo.setMessage("查询失败：内部错误");
			response.setReturnInfo(returnInfo);
		}
		return response;
	}

	  private int transferStatus(String status){
		if(StringUtils.isNotBlank(status)){
			return Integer.parseInt(status);
		} else {
			return 0;
		}
	 }

	@Override
	public CompensateRecordResponse getCompensateRecordById(String id) {
		CompensateRecordResponse response = new CompensateRecordResponse();
		ReturnInfo returnInfo = new ReturnInfo();
		
		try {
			CompensateRecordVo record = compensateRecordService.getComRecordVoById(id);
			response.setCompensateRecord(record);
			returnInfo.setCode(HttpStatus.OK.getStatusCode());
			response.setReturnInfo(returnInfo);
			
			if (record == null) {
				returnInfo.setMessage("该用户没有理赔信息");
				response.setReturnInfo(returnInfo);
			}
		} catch (Exception e) {
			logger.error("getCompensateRecordByPolicyIDandStatus异常", e);
			returnInfo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
			returnInfo.setMessage("查询失败：内部错误");
			response.setReturnInfo(returnInfo);
		}
		return response;
	}
}
