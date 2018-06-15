package com.qding.insurance.picc.bean;


/**
 * PICC接口调用返回xml根节点ReturnInfo
 * @author zhangxiaojun
 *
 */
public class ReturnInfo {
	/** GeneralInfoReturn节点 */
	private GeneralInfoReturn generalInfoReturn;
	/** PolicyInfoReturns节点 */
	private PolicyInfoReturns policyInfoReturns;
	
	public GeneralInfoReturn getGeneralInfoReturn() {
		return generalInfoReturn;
	}
	public void setGeneralInfoReturn(
			GeneralInfoReturn generalInfoReturn) {
		this.generalInfoReturn = generalInfoReturn;
	}
	public PolicyInfoReturns getPolicyInfoReturns() {
		return policyInfoReturns;
	}
	public void setPolicyInfoReturns(
			PolicyInfoReturns policyInfoReturns) {
		this.policyInfoReturns = policyInfoReturns;
	}
	
}
