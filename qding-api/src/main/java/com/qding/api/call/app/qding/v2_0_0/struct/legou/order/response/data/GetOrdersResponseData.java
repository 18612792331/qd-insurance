package com.qding.api.call.app.qding.v2_0_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderBean;
import com.qding.api.struct.ResponseData;

import java.util.List;
import java.util.Map;


public class GetOrdersResponseData extends ResponseData{

	private static final long serialVersionUID = 5865375817476963729L;

	@ExplainAnnotation(explain = "当前记录数")
	private int recordCount;

	@ExplainAnnotation(explain = "总记录数")
	private int totalCount;

	@ExplainAnnotation(explain = "订单列表")
	private List<OrderBean> list;

	@ExplainAnnotation(explain = "异步请求业态订单参数",desc = "json串格式")
	private String orderParametere;

	@ExplainAnnotation(explain = "是否支持兑换码按钮",desc = "1:支持，0：不支持")
	private int isShowRedeemCode = Integer.valueOf(0);

	public int getIsShowRedeemCode() {
		return isShowRedeemCode;
	}

	public void setIsShowRedeemCode(int isShowRedeemCode) {
		this.isShowRedeemCode = isShowRedeemCode;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<OrderBean> getList() {
		return list;
	}

	public void setList(List<OrderBean> list) {
		this.list = list;
	}

	public String getOrderParametere() {
		return orderParametere;
	}

	public void setOrderParametere(String orderParametere) {
		this.orderParametere = orderParametere;
	}

	@Override
	public String toString() {
		return "GetOrdersResponseData{" +
				"recordCount=" + recordCount +
				", totalCount=" + totalCount +
				", list=" + list +
				", orderParametere='" + orderParametere + '\'' +
				", isShowRedeemCode=" + isShowRedeemCode +
				'}';
	}
}
