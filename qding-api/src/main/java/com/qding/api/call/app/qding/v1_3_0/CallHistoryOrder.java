package com.qding.api.call.app.qding.v1_3_0;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.history.order.request.GetOrderRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.history.order.request.GetOrdersRequest;
import com.qding.framework.common.http.QDHttpClientService;

/**
 * 2.0的market历史订单
 * @author lichao
 *
 */
public class CallHistoryOrder extends Callable{

	private String historyGetMarketOrdersUrl;
	
	private String historyGetMarketOrderUrl;
	
	
	@HTTP(alias="getOrders")
	public String getOrders(GetOrdersRequest request) {
		
		return QDHttpClientService.sendGetRequest(String.format(historyGetMarketOrdersUrl, 
				request.getAccountId(), request.getProjectId(), request.getPageNo(), request.getPageSize()), "utf-8");
		
	}
	
	@HTTP(alias="getOrder")
	public String getOrder(GetOrderRequest request) {
		
		return QDHttpClientService.sendGetRequest(String.format(historyGetMarketOrderUrl, 
				request.getOrderId()), "utf-8");
		
	}

	public String getHistoryGetMarketOrdersUrl() {
		return historyGetMarketOrdersUrl;
	}

	public void setHistoryGetMarketOrdersUrl(String historyGetMarketOrdersUrl) {
		this.historyGetMarketOrdersUrl = historyGetMarketOrdersUrl;
	}

	public String getHistoryGetMarketOrderUrl() {
		return historyGetMarketOrderUrl;
	}

	public void setHistoryGetMarketOrderUrl(String historyGetMarketOrderUrl) {
		this.historyGetMarketOrderUrl = historyGetMarketOrderUrl;
	}
	
}
