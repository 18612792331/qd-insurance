package com.qding.api.call.app.qding.v2_0_0.struct.familypayment.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class CreateFamilyRelationRequest extends BaseRequest {
    private static final long serialVersionUID = 1065228792967611117L;

    @NotNullValidate
    private String mobile;

    @NotNullValidate
    @ExplainAnnotation(explain = "亲情支付关系",desc = "MOTHER:母亲; FATHER:父亲; WIFE:老婆; HUSBAND:老公; SON:儿子; DAUGHTER:女儿; RELATIVE:亲属; FRIEND:朋友")
    private String type;

    @NotNullValidate
    @ExplainAnnotation(explain = "开通者ID")
    private String midFrom;

    public CreateFamilyRelationRequest() {
    }

    public CreateFamilyRelationRequest(String mobile, String type, String midFrom) {
        this.mobile = mobile;
        this.type = type;
        this.midFrom = midFrom;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMidFrom() {
        return midFrom;
    }

    public void setMidFrom(String midFrom) {
        this.midFrom = midFrom;
    }

    @Override
    public String toString() {
        return "createFamilyRelationRequest{" +
                "mobile='" + mobile + '\'' +
                ", type='" + type + '\'' +
                ", midFrom='" + midFrom + '\'' +
                '}';
    }
}
