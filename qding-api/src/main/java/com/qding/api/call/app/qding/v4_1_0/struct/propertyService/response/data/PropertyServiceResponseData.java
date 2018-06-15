package com.qding.api.call.app.qding.v4_1_0.struct.propertyService.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v4_0_0.struct.chatRecord.ChatRecordResultDTO;
import com.qding.api.call.app.qding.v4_1_0.struct.propertyService.PropertyServiceResultDTO;
import com.qding.api.struct.ResponseData;

/**
 * Created by zhangxiaojun on 2018/6/4.
 */
public class PropertyServiceResponseData extends ResponseData {
	private static final long serialVersionUID = 9153545829841806312L;
	
	@ExplainAnnotation(explain = "社区服务分类列表")
	private List<PropertyServiceResultDTO> resultList;

	public List<PropertyServiceResultDTO> getResultList() {
		return resultList;
	}

	public void setResultList(List<PropertyServiceResultDTO> resultList) {
		this.resultList = resultList;
	}


}
