package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 通过报事ID获取报事回执列表					
 * @author lichao
 *
 */
@Validate
public class GetReportReceiptRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4863962289617576842L;
 
	@NotNullValidate
	private String reportId;
	
	public GetReportReceiptRequest() {

	}

	public GetReportReceiptRequest(String reportId) {
		super();
		this.reportId = reportId;
	}

	@Override
	public String toString() {
		return "GetReportReceiptRequest [reportId=" + reportId
				+ ", toString()=" + super.toString() + "]";
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	
}
