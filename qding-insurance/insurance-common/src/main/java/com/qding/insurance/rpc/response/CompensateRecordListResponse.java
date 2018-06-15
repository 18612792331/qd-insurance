package com.qding.insurance.rpc.response;

import java.util.List;

import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.insurance.vo.CompensateRecordVo;
//用户理赔列表
public class CompensateRecordListResponse extends BaseResponse{

	
	private static final long serialVersionUID = -4971856534148565355L;
	
	private List<CompensateRecordVo> compensateRecordList;

	public List<CompensateRecordVo> getCompensateRecordList() {
		return compensateRecordList;
	}

	public void setCompensateRecordList(List<CompensateRecordVo> compensateRecordList) {
		this.compensateRecordList = compensateRecordList;
	}
	
	
	
	

}
