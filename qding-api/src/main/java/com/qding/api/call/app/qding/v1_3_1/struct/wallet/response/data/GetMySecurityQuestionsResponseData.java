package com.qding.api.call.app.qding.v1_3_1.struct.wallet.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_1.struct.wallet.GetSecurityQuestion;
import com.qding.api.struct.ResponseData;

public class GetMySecurityQuestionsResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1028218996312291843L;

	private List<GetSecurityQuestion> list;
	
	public GetMySecurityQuestionsResponseData() {

	}

	public List<GetSecurityQuestion> getList() {
		return list;
	}

	public void setList(List<GetSecurityQuestion> list) {
		this.list = list;
	}
	
}
