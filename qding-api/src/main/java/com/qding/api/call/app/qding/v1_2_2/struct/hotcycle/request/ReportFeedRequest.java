package com.qding.api.call.app.qding.v1_2_2.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class ReportFeedRequest  extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2722526382314594011L;
	
	/**
	 * 被举报信息ID
	 */
	private String feedId;
	
	/**
	 * 举报者ID
	 */
	private String reportUserId;
	
	/**
	 * 举报类型
	 */
	private Integer reportType;

	/**
	 * @return the feedId
	 */
	public String getFeedId() {
		return feedId;
	}

	/**
	 * @param feedId the feedId to set
	 */
	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}

	/**
	 * @return the reportUserId
	 */
	public String getReportUserId() {
		return reportUserId;
	}

	/**
	 * @param reportUserId the reportUserId to set
	 */
	public void setReportUserId(String reportUserId) {
		this.reportUserId = reportUserId;
	}

	/**
	 * @return the reportType
	 */
	public Integer getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}
	
	
}
