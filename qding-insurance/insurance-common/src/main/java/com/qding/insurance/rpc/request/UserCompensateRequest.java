package com.qding.insurance.rpc.request;

import java.util.Date;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class UserCompensateRequest extends BaseRequest {

    private static final long serialVersionUID = -3864718395809030684L;

    // 用户Mid
    private String mid;

    // 保单ID
    private String policyId;

    // 事故类型ID
    private String itemId;

    // 联系人姓名
    private String contactName;

    // 联系人电话
    private String contactPhone;

    // 出险时间
    private Date happenAt;

    // 估损金额
    private String estimateMoney;

    // 事故描述
    private String accidentMemo;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getHappenAt() {
        return happenAt;
    }

    public void setHappenAt(Date happenAt) {
        this.happenAt = happenAt;
    }

    public String getEstimateMoney() {
        return estimateMoney;
    }

    public void setEstimateMoney(String estimateMoney) {
        this.estimateMoney = estimateMoney;
    }

    public String getAccidentMemo() {
        return accidentMemo;
    }

    public void setAccidentMemo(String accidentMemo) {
        this.accidentMemo = accidentMemo;
    }

}
