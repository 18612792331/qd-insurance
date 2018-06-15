package com.qding.api.call.app.qding.v1_2_2;

import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_2_2.struct.hotcycle.request.ReportFeedRequest;
import com.qding.api.call.app.qding.v1_2_2.struct.hotcycle.response.data.ReportFeedResponseData;
import com.qding.api.struct.Response;
import com.qding.framework.common.exception.ServiceException;
import com.qding.hotcycle.service.IHotCycleRemoteService;
import com.qding.hotcycle.struct.request.GetReportRequest;
import com.qding.hotcycle.struct.response.GetReportResponse;

public class CallHotcycle extends com.qding.api.call.app.qding.v1_2_1.CallHotcycle{

	@Autowired
	private IHotCycleRemoteService hotcycleService;
	
	/**
	 * 举报feed 1.2.2
	 * @param request
	 * @return
	 */
	@HTTP(alias="reportFeed")
	@Deprecated
	public Response<ReportFeedResponseData> reportFeed(ReportFeedRequest request) {
		
		try {
			Response<ReportFeedResponseData> response = new Response<ReportFeedResponseData>();
			
			GetReportResponse reportResponse = hotcycleService.saveReport(transfor(GetReportRequest.class, request));
			
			checkAndContinue(reportResponse);
			
			response.setData(new ReportFeedResponseData());
			
			return response;
			
		} catch (ServiceException e) {
			
			return handleException(ReportFeedResponseData.class, e);
		}
	}
}
