package com.qding.insurance.picc.bean;

/**
 * 投保请求报文中的PolicyInfo节点
 * @author zhangxiaojun
 *
 */
public class PolicyInfo {
	//请求序列号
	private String serialNo;
	//人保险种代码
	private String riskCode;
	//售出保险时间
	private String operateTimes;
	//起保日期
	private String startDate;
	//终保日期
	private String endDate;
	//起保小时
	private String startHour;
	//终保小时
	private String endHour;
	//保单总保险金额
	private String sumAmount;
	//保单总保险费
	private String sumPremium;
	//争议解决方式
	private String arguSolution;
	//财产地址/标的地址
	private String houseAddress;
	//支付方式
	private String payMode;
	//投保份数
	private String quantity;
	//投保方案
	private InsuredPlan insuredPlan;
	//投保人信息
	private Applicant applicant;
	//被保险人信息
	private Insureds insureds;
	
	public String getOperateTimes() {
		return operateTimes;
	}
	public void setOperateTimes(String operateTimes) {
		this.operateTimes = operateTimes;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getSumPremium() {
		return sumPremium;
	}
	public void setSumPremium(String sumPremium) {
		this.sumPremium = sumPremium;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	public InsuredPlan getInsuredPlan() {
		return insuredPlan;
	}
	public void setInsuredPlan(InsuredPlan insuredPlan) {
		this.insuredPlan = insuredPlan;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public Insureds getInsureds() {
		return insureds;
	}
	public void setInsureds(Insureds insureds) {
		this.insureds = insureds;
	}
	
	
}
