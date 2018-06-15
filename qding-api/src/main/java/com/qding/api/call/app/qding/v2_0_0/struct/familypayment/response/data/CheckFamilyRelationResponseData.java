package com.qding.api.call.app.qding.v2_0_0.struct.familypayment.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

public class CheckFamilyRelationResponseData extends ResponseData {

    private int status = 100;

    @ExplainAnnotation(explain = "会员信息")
    private com.qding.api.call.app.qding.v1_3_0.struct.user.Member member;

    public com.qding.api.call.app.qding.v1_3_0.struct.user.Member getMember() {
        return member;
    }

    public void setMember(com.qding.api.call.app.qding.v1_3_0.struct.user.Member member) {
        this.member = member;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CheckFamilyRelationResponseData() {
    }

    public CheckFamilyRelationResponseData(String message) {
        super.setMessage(message);
    }


    @Override
    public String toString() {
        return "CheckFamilyRelationResponseData{" +
                "status=" + status +
                ", member=" + member +
                '}';
    }
}
