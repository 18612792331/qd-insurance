package com.qding.insurance.rpc.response;

import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.insurance.vo.CompensateRecordVo;

public class CompensateRecordResponse extends BaseResponse{

	private static final long serialVersionUID = 7521437823783316940L;
	
	private CompensateRecordVo compensateRecord;

	public CompensateRecordVo getCompensateRecord() {
		return compensateRecord;
	}

	public void setCompensateRecord(CompensateRecordVo compensateRecord) {
		this.compensateRecord = compensateRecord;
	}

	

}
