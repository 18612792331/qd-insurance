package com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

@Validate
public class GetPropertyBillsIndexRequest extends BaseRequest{

	private static final long serialVersionUID = -2024928322741576103L;

	@RangeValueValidate(max="20", min="1")
	private Integer pageSize = 10;
	
	@MinValueValidate(value="1")
	private Integer pageNo = 1;
	
	@NotNullValidate
	private String roomId;
	
	//0 全部 1未缴
	@NotNullValidate
	private Integer feeStatus;

	//这里设置成非必填字段，保证支付宝服务窗那块不登陆也能查看房屋未缴物业费
	private String memberId;
	
	public GetPropertyBillsIndexRequest() {

	}

	public GetPropertyBillsIndexRequest(Integer pageSize, Integer pageNo, String roomId,String memberId, Integer feeStatus) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.roomId = roomId;
		this.feeStatus = feeStatus;
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Integer getFeeStatus() {
		return feeStatus;
	}
	
	public void setFeeStatus(Integer feeStatus) {
		this.feeStatus = feeStatus;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@Override
	public String toString() {
		return "GetPropertyBillsIndexRequest [memeber="+memberId+",pageSize=" + pageSize
				+ ", pageNo=" + pageNo + ", roomId=" + roomId + ", toString()="
				+ super.toString() + "]";
	}
}
