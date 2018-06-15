package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.ReportReceipt;
import com.qding.api.struct.ResponseData;

/**
 * 通过报事ID获取报事回执列表					
 * @author lichao
 *
 */
public class GetReportReceiptResponseData extends ResponseData{

	private static final long serialVersionUID = -8233808224284727963L;

	@ExplainAnnotation (explain = "处理进度列表")
	private List<ReportReceipt> list;
	
	public GetReportReceiptResponseData() {
	}

	public GetReportReceiptResponseData(List<ReportReceipt> list) {
		super();
		this.list = list;
	}

	public List<ReportReceipt> getList() {
		return list;
	}

	public void setList(List<ReportReceipt> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetReportReceiptResponseData [list=" + list + ", toString()="
				+ super.toString() + "]";
	}
	
}
