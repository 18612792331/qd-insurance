package com.qding.api.call.app.qding.v2_0_0.struct.familypayment.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class RelieveFamilyRelationRequest extends BaseRequest {
    private static final long serialVersionUID = 1065228792967611117L;

    @NotNullValidate
    @ExplainAnnotation(explain = "开通者ID")
    private String midFrom;

    @NotNullValidate
    @ExplainAnnotation(explain = "使用者ID")
    private String midTo;

    @NotNullValidate
    private String type;

    public RelieveFamilyRelationRequest() {
    }

    public RelieveFamilyRelationRequest(String midFrom, String midTo, String type) {
        this.midFrom = midFrom;
        this.midTo = midTo;
        this.type = type;
    }

    public String getMidFrom() {
        return midFrom;
    }

    public void setMidFrom(String midFrom) {
        this.midFrom = midFrom;
    }

    public String getMidTo() {
        return midTo;
    }

    public void setMidTo(String midTo) {
        this.midTo = midTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RelieveFamilyRelationRequest{" +
                "midFrom='" + midFrom + '\'' +
                ", midTo='" + midTo + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
