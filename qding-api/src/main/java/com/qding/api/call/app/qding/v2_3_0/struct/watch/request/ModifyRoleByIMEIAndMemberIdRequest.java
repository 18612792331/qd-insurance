package com.qding.api.call.app.qding.v2_3_0.struct.watch.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class ModifyRoleByIMEIAndMemberIdRequest extends BaseRequest {

    private static final long serialVersionUID = -1862991276667031315L;

    @NotNullValidate
    @ExplainAnnotation(explain = "绑定人memberId")
    private String memberId = "";

    @NotNullValidate
    @ExplainAnnotation(explain = "被绑定手表串号")
    private String imei = "";

    @NotNullValidate
    @ExplainAnnotation(explain = "绑定人角色", desc = "1:妈妈; 2:爸爸; 3:爷爷; 4:奶奶; 5:外公; 6:外婆; 7:其他亲属;")
    private String role;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ModifyRoleByIMEIAndMemberIdRequest{" +
                "memberId='" + memberId + '\'' +
                ", imei='" + imei + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
