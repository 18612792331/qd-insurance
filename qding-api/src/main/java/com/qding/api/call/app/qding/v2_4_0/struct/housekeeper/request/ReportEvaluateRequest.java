package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.ReportEvaluateBean;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.TaskEvaluateBean;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;


@Validate
public class ReportEvaluateRequest extends BaseRequest{

	private static final long serialVersionUID = 2750419735041934367L;

	@NotNullValidate
	@ExplainAnnotation (explain = "报事ID")
	private String reportId;

	@ExplainAnnotation (explain = "报事评价",desc = "针对好评，中评该字段必须填充数据")
	private ReportEvaluateBean  reportEvaluateEntity;

	@ExplainAnnotation (explain = "工单评价列表",desc = "选择报事差评时，该字段必须填充数据")
	private List<TaskEvaluateBean> taskEvaluateBeanList;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public List<TaskEvaluateBean> getTaskEvaluateBeanList() {
		return taskEvaluateBeanList;
	}

	public void setTaskEvaluateBeanList(List<TaskEvaluateBean> taskEvaluateBeanList) {
		this.taskEvaluateBeanList = taskEvaluateBeanList;
	}

	public ReportEvaluateBean getReportEvaluateEntity() {
		return reportEvaluateEntity;
	}

	public void setReportEvaluateEntity(ReportEvaluateBean reportEvaluateEntity) {
		this.reportEvaluateEntity = reportEvaluateEntity;
	}

	@Override
	public String toString() {
		return "ReportEvaluateRequest{" +
				"reportId='" + reportId + '\'' +
				", reportEvaluateEntity=" + reportEvaluateEntity +
				", taskEvaluateBeanList=" + taskEvaluateBeanList +
				'}';
	}
}
