package com.qding.insurance.picc.bean;

/**
 * PICC接口调用返回xml子节点InsuredReturn
 * @author zhangxiaojun
 *
 */
public class InsuredReturn {
	/**被保险人序列号*/
	private String insuredSeqNo;
	/**被保险人处理结果 （校验成功时，节点值为”00”）*/
	private String checkResult;
	/**被保险人处理结果描述*/
	private String checkMessage;
	
	public String getInsuredSeqNo() {
		return insuredSeqNo;
	}
	public void setInsuredSeqNo(String insuredSeqNo) {
		this.insuredSeqNo = insuredSeqNo;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getCheckMessage() {
		return checkMessage;
	}
	public void setCheckMessage(String checkMessage) {
		this.checkMessage = checkMessage;
	}
	
}
