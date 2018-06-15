package com.qding.insurance.param;

public class InsurancePolicyParam extends PageParam {

    private static final long serialVersionUID = -6889429607953779783L;

    /**
     * 单证ID
     */
    private String policyId;

    /**
     * 产品名称
     */
    private String wareName;

    /**
     * 保单状态 1：投保中，2：未生效，3：投保失败，4：已生效，5：已到期，6：已终止
     */

    private Integer policyStatus;

    /**
     * 投保人电话
     */
    private String memberPhone;

    /**
     * 保单号
     */
    private String piccNo;

    /**
     * 生效开始时间
     */
    private String actAtStartTime;

    /**
     * 生效结束时间
     */
    private String actAtEndTime;

    /**
     * 到期开始时间
     */
    private String expireAtStartTime;

    /**
     * 到期结束时间
     */
    private String expireAtEndTime;

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public Integer getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(Integer policyStatus) {
        this.policyStatus = policyStatus;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getPiccNo() {
        return piccNo;
    }

    public void setPiccNo(String piccNo) {
        this.piccNo = piccNo;
    }

    public String getActAtStartTime() {
        return actAtStartTime;
    }

    public void setActAtStartTime(String actAtStartTime) {
        this.actAtStartTime = actAtStartTime;
    }

    public String getActAtEndTime() {
        return actAtEndTime;
    }

    public void setActAtEndTime(String actAtEndTime) {
        this.actAtEndTime = actAtEndTime;
    }

    public String getExpireAtStartTime() {
        return expireAtStartTime;
    }

    public void setExpireAtStartTime(String expireAtStartTime) {
        this.expireAtStartTime = expireAtStartTime;
    }

    public String getExpireAtEndTime() {
        return expireAtEndTime;
    }

    public void setExpireAtEndTime(String expireAtEndTime) {
        this.expireAtEndTime = expireAtEndTime;
    }

}
