package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data;

import java.math.BigDecimal;
import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.PropertyBills;
import com.qding.api.struct.ResponseData;

/**
 * 根据roomId查询物业费缴费详情
 * @author lichao
 *
 */
public class GetRoomPropertyBillsResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6806038544150193190L;
	
	private List<PropertyBills> list;

	/**
	 * 是否全部都缴费
	 */
	private boolean unpaid = false;

	/**
	 * 未付款总额
	 */
	private BigDecimal unpaidCount;


	public GetRoomPropertyBillsResponseData() {

	}

	public GetRoomPropertyBillsResponseData(List<PropertyBills> list) {
		super();
		this.list = list;
	}

	public List<PropertyBills> getList() {
		return list;
	}

	public void setList(List<PropertyBills> list) {
		this.list = list;
	}

	public boolean isUnpaid() {
		return unpaid;
	}

	public void setUnpaid(boolean unpaid) {
		this.unpaid = unpaid;
	}

	public BigDecimal getUnpaidCount() {
		return unpaidCount;
	}

	public void setUnpaidCount(BigDecimal unpaidCount) {
		this.unpaidCount = unpaidCount;
	}

	@Override
	public String toString() {
		return "GetRoomPropertyBillsResponseData [unpaid ="+unpaid+",list=" + list
				+ ", toString()=" + super.toString() + "]";
	}
	
}
