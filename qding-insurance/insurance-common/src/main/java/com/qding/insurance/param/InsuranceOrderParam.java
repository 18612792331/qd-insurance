package com.qding.insurance.param;

public class InsuranceOrderParam extends PageParam {

    private static final long serialVersionUID = -1937316579361485459L;

    private String cityId; // 城市ID
    
    private String projectName; // 社区名称
    
    private String orderNo; // 订单号
     
    private String orderStatus; //订单状态，1：未支付， 2：已支付， 3：已完成， 4：已取消
    
    private String payStatus; //支付状态，1：未支付， 2：已支付， 3：已退款
    
    private String memberName;	//投保人姓名
    
    private String memberPhone;	//投保人电话
    
    private String providerName; // 供方
    
    private String paidAtBegin; // 付款时间开始，格式 yyyy-MM-dd HH:mm:ss
    
    private String paidAtEnd; // 付款时间结束 

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getPaidAtBegin() {
		return paidAtBegin;
	}

	public void setPaidAtBegin(String paidAtBegin) {
		this.paidAtBegin = paidAtBegin;
	}

	public String getPaidAtEnd() {
		return paidAtEnd;
	}

	public void setPaidAtEnd(String paidAtEnd) {
		this.paidAtEnd = paidAtEnd;
	}

    
    
}
