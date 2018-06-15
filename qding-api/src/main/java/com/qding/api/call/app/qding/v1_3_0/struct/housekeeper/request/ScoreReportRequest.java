package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 报事评价					
 * @author lichao
 *
 */
@Validate
public class ScoreReportRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2750419735041934367L;
 
	@NotNullValidate
	private String reportId;
	
	@MinValueValidate(value="1")
	private Integer score;
	
	public ScoreReportRequest() {

	}

	public ScoreReportRequest(String reportId, Integer score) {
		super();
		this.reportId = reportId;
		this.score = score;
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
		return "CommentReportRequest [reportId=" + reportId + ", score="
				+ score + ", toString()=" + super.toString() + "]";
	}
	
}
