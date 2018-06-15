package com.qding.insurance.picc.bean;

/**
 * PICC接口调用返回xml子节点PolicyInfoReturn
 * @author zhangxiaojun
 *
 */
public class PolicyInfoReturn {
	/**请求序列号*/
	private String serialNo;
	/**保单号*/
	private String policyNo;
	/**保单查询链接*/
	private String policyUrl;
	/**电子保单下载地址*/
	private String downloadUrl;
	/**此投保单公共信息处理结果 （校验报文中从<SerialNo>至</Applicant>节点的信息，校验成功时，节点值为“00”）*/
	private String saveResult;
	/**公共信息处理结果描述*/
	private String saveMessage;
	/**处理结束时间*/
	private String saveTimes;
	/** InsuredReturns节点 */
	private InsuredReturns insuredReturns;
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getPolicyUrl() {
		return policyUrl;
	}
	public void setPolicyUrl(String policyUrl) {
		this.policyUrl = policyUrl;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getSaveResult() {
		return saveResult;
	}
	public void setSaveResult(String saveResult) {
		this.saveResult = saveResult;
	}
	public String getSaveMessage() {
		return saveMessage;
	}
	public void setSaveMessage(String saveMessage) {
		this.saveMessage = saveMessage;
	}
	public String getSaveTimes() {
		return saveTimes;
	}
	public void setSaveTimes(String saveTimes) {
		this.saveTimes = saveTimes;
	}
	public InsuredReturns getInsuredReturns() {
		return insuredReturns;
	}
	public void setInsuredReturns(InsuredReturns insuredReturns) {
		this.insuredReturns = insuredReturns;
	}
	
}
