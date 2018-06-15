package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 报事评价					
 * @author lichao
 *
 */
@Validate
public class ReportEvaluateRequest extends BaseRequest{

	private static final long serialVersionUID = 2750419735041934367L;
 
	@NotNullValidate
	private String reportId;

	@NotNullValidate
	private Integer score = 0;

	@NotNullValidate
	private String content;
	
	public ReportEvaluateRequest() {

	}

	public ReportEvaluateRequest(String reportId, Integer score,String content) {
		this.reportId = reportId;
		this.score = score;
		this.content=content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ReportEvaluateRequest [reportId=" + reportId + ", score="
				+ score + ", content="+content+",toString()=" + super.toString() + "]";
	}
	
}
