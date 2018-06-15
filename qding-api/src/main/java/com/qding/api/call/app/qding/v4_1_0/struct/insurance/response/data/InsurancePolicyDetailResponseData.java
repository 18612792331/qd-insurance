package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import java.util.List;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

public class InsurancePolicyDetailResponseData extends ResponseData {

    private static final long serialVersionUID = 3759588319312241086L;

    @ExplainAnnotation(explain = "保单ID")
    private String policyId;

    @ExplainAnnotation(explain = "picc订单号")
    private String policyNo;

    @ExplainAnnotation(explain = "保单状态")
    private String status;

    @ExplainAnnotation(explain = "商品名称")
    private String wareName;

    @ExplainAnnotation(explain = "被保险人名称")
    private String insurantName;

    @ExplainAnnotation(explain = "被保险财产（房屋）地址")
    private String roomAddress;

    @ExplainAnnotation(explain = "生效日期")
    private String effectTime;

    @ExplainAnnotation(explain = "到期日期")
    private String expireTime;

    @ExplainAnnotation(explain = "承保公司")
    private String insuranceCompany;

    @ExplainAnnotation(explain = "投保人名称")
    private String policyHolderName;

    @ExplainAnnotation(explain = "收益人名称")
    private String benefitName;

    @ExplainAnnotation(explain = "电子保单地址")
    private String policyUrl;

    @ExplainAnnotation(explain = "保费总额")
    private String paidMoney;

    @ExplainAnnotation(explain = "保单责任列表，剩余权益也使用这个列表")
    private List<PolicyDutyResponseData> list;

    public String getPolicyUrl() {
        return policyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(String paidMoney) {
        this.paidMoney = paidMoney;
    }

    public String getBenefitName() {
        return benefitName;
    }

    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }

    public String getPolicyHolderName() {
        return policyHolderName;
    }

    public void setPolicyHolderName(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }

    public List<PolicyDutyResponseData> getList() {
        return list;
    }

    public void setList(List<PolicyDutyResponseData> list) {
        this.list = list;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
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

    public String getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

}
