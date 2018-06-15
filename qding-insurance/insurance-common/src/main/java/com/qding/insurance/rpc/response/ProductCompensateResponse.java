package com.qding.insurance.rpc.response;

import com.qding.framework.common.api.struct.response.BaseResponse;

public class ProductCompensateResponse extends BaseResponse {

    private static final long serialVersionUID = -5394543917075721464L;

    /**
     * 申请结果 OK:可理赔，REFUSE:拒绝理赔，WAITING-AUTH:待人保授权
     */
    private String result;

    /**
     * 保险单位名称
     */
    private String insuranceProviderName;

    /**
     * 保单编号（人保）
     */
    private String policyNo;

    /**
     * 理赔方式 TIMES:按次数，MONEY：按金额
     */
    private String compensateType;

    /**
     * 理赔金额
     */
    private String compensateMoney;

    /**
     * 拒绝原因
     */
    private String refuseReason;

    public String getCompensateType() {
        return compensateType;
    }

    public void setCompensateType(String compensateType) {
        this.compensateType = compensateType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInsuranceProviderName() {
        return insuranceProviderName;
    }

    public void setInsuranceProviderName(String insuranceProviderName) {
        this.insuranceProviderName = insuranceProviderName;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getCompensateMoney() {
        return compensateMoney;
    }

    public void setCompensateMoney(String compensateMoney) {
        this.compensateMoney = compensateMoney;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

}
