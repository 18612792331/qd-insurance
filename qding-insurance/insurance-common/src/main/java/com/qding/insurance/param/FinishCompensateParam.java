package com.qding.insurance.param;

import java.io.Serializable;

public class FinishCompensateParam implements Serializable {

    private static final long serialVersionUID = -759147773187040762L;

    private String id;

    // 报案号
    private String reportNo;

    // *出险时间
    private String happenAt;

    // *联系人姓名
    private String contactName;

    // *联系人电话
    private String contactPhone;

    // *次数/金额
    private String paidMoney;

    // 事故描述
    private String accidentMemo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getHappenAt() {
        return happenAt;
    }

    public void setHappenAt(String happenAt) {
        this.happenAt = happenAt;
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

    public String getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(String paidMoney) {
        this.paidMoney = paidMoney;
    }

    public String getAccidentMemo() {
        return accidentMemo;
    }

    public void setAccidentMemo(String accidentMemo) {
        this.accidentMemo = accidentMemo;
    }

}
