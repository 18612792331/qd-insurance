package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
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

	private static final long serialVersionUID = -4863962289617576842L;
 
	@ExplainAnnotation (explain = "工单ID",desc = "龙湖不传，非龙湖涉及到查询工单回执列表时必传")
	private String taskId;

	@NotNullValidate
	@ExplainAnnotation (explain = "报事ID")
	private String reportId;


	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public GetReportReceiptRequest() {

	}
}
