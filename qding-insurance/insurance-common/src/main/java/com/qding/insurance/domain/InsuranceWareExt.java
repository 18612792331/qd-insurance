package com.qding.insurance.domain;

public class InsuranceWareExt extends InsuranceWare{

    private String subjectSummary; // 主题摘要

    private String notes; //投保须知

    private String clause;//保险条款

    private String agreement;//服务协议

    private String moduleTile1;//自定义模块一标题

    private String moduleTile2;//自定义模块二标题

    private String moduleTile3;//自定义模块三标题


    public String getSubjectSummary() {
        return subjectSummary;
    }

    public void setSubjectSummary(String subjectSummary) {
        this.subjectSummary = subjectSummary == null ? null : subjectSummary.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause == null ? null : clause.trim();
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement == null ? null : agreement.trim();
    }

    public String getModuleTile1() {
        return moduleTile1;
    }

    public void setModuleTile1(String moduleTile1) {
        this.moduleTile1 = moduleTile1 == null ? null : moduleTile1.trim();
    }

    public String getModuleTile2() {
        return moduleTile2;
    }

    public void setModuleTile2(String moduleTile2) {
        this.moduleTile2 = moduleTile2 == null ? null : moduleTile2.trim();
    }

    public String getModuleTile3() {
        return moduleTile3;
    }

    public void setModuleTile3(String moduleTile3) {
        this.moduleTile3 = moduleTile3 == null ? null : moduleTile3.trim();
    }
}