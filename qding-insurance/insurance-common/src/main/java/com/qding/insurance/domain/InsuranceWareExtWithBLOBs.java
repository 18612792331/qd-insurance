package com.qding.insurance.domain;

public class InsuranceWareExtWithBLOBs extends InsuranceWareExt {
    private String guaranteeIntroduce;//保障计划介绍

    private String guaranteeDetail;//保障详情

    private String moduleContent1;//自定义模块一内容

    private String moduleContent2;//自定义模块二内容

    private String moduleContent3;//自定义模块三内容

    public String getGuaranteeIntroduce() {
        return guaranteeIntroduce;
    }

    public void setGuaranteeIntroduce(String guaranteeIntroduce) {
        this.guaranteeIntroduce = guaranteeIntroduce == null ? null : guaranteeIntroduce.trim();
    }

    public String getGuaranteeDetail() {
        return guaranteeDetail;
    }

    public void setGuaranteeDetail(String guaranteeDetail) {
        this.guaranteeDetail = guaranteeDetail == null ? null : guaranteeDetail.trim();
    }

    public String getModuleContent1() {
        return moduleContent1;
    }

    public void setModuleContent1(String moduleContent1) {
        this.moduleContent1 = moduleContent1 == null ? null : moduleContent1.trim();
    }

    public String getModuleContent2() {
        return moduleContent2;
    }

    public void setModuleContent2(String moduleContent2) {
        this.moduleContent2 = moduleContent2 == null ? null : moduleContent2.trim();
    }

    public String getModuleContent3() {
        return moduleContent3;
    }

    public void setModuleContent3(String moduleContent3) {
        this.moduleContent3 = moduleContent3 == null ? null : moduleContent3.trim();
    }
}