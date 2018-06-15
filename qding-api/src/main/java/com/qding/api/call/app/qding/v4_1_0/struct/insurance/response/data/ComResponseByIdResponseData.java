package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

public class ComResponseByIdResponseData extends ResponseData{

	private static final long serialVersionUID = -2491954655660473358L;
	
	
	@ExplainAnnotation (explain = "商品名称")
	private String wareName;
	
	@ExplainAnnotation (explain = "理赔金额/次数")
	private String compensateAmount;
	
	@ExplainAnnotation (explain = "理赔状态")
	private String compensateStatus;
	
	@ExplainAnnotation (explain = "申请时间")
	private String createTime;
	
	@ExplainAnnotation (explain = "完成时间")
	private String finishTime;
	
	@ExplainAnnotation (explain = "被保险人名称")
	private String insurantName;
	
	@ExplainAnnotation (explain = "保险财产地址")
	private String roomAddress;

	public String getWareName() {
		return wareName;
	}

	public void setWareName(String wareName) {
		this.wareName = wareName;
	}

	public String getCompensateAmount() {
		return compensateAmount;
	}

	public void setCompensateAmount(String compensateAmount) {
		this.compensateAmount = compensateAmount;
	}

	public String getCompensateStatus() {
		return compensateStatus;
	}

	public void setCompensateStatus(String compensateStatus) {
		this.compensateStatus = compensateStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getInsurantName() {
		return insurantName;
	}

	public void setInsurantName(String insurantName) {
		this.insurantName = insurantName;
	}

	public String getRoomAddress() {
		return roomAddress;
	}

	public void setRoomAddress(String roomAddress) {
		this.roomAddress = roomAddress;
	}
	
	

}
