package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import java.util.List;

import com.qding.api.struct.ResponseData;

public class CompensateRecordResopnseData extends ResponseData{

	private static final long serialVersionUID = -2974435995341018354L;
    
	private List<CompensateSingleResopnseData> list;

	public List<CompensateSingleResopnseData> getList() {
		return list;
	}

	public void setList(List<CompensateSingleResopnseData> list) {
		this.list = list;
	}

	
	
}
