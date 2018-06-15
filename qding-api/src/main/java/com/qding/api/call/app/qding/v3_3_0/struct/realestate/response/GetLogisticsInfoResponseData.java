package com.qding.api.call.app.qding.v3_3_0.struct.realestate.response;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.EvaluateBody;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.LogisticsInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetLogisticsInfoResponseData extends ResponseData {

    private static final long serialVersionUID = 2108010926604691864L;

    @ExplainAnnotation (explain = "物流信息列表")
    private List<LogisticsInfo> list;

	public List<LogisticsInfo> getList() {
		return list;
	}

	public void setList(List<LogisticsInfo> list) {
		this.list = list;
	}

	public GetLogisticsInfoResponseData(List<LogisticsInfo> list) {
		this.list = list;
	}

	public GetLogisticsInfoResponseData() {
	}
}
