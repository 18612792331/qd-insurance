package com.qding.api.call.app.qding.v2_0_0.struct.familypayment.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetFamilyRelationListRequest extends BaseRequest {

    @NotNullValidate
    @ExplainAnnotation(explain = "亲情支付-身份类型",desc = "1:开通者; 2:使用者")
    private String type;

    @NotNullValidate
    @ExplainAnnotation(explain = "会员ID")
    private String mid;

    @ExplainAnnotation(explain = "建立关系起始时间")
    private Long createTimeBegin;

    @ExplainAnnotation(explain = "建立关系终止时间")
    private Long createTimeEnd;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Long getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Long createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Long getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Long createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public GetFamilyRelationListRequest() {
    }

    public GetFamilyRelationListRequest(String type, String mid, Long createTimeBegin, Long createTimeEnd) {
        this.type = type;
        this.mid = mid;
        this.createTimeBegin = createTimeBegin;
        this.createTimeEnd = createTimeEnd;
    }

    @Override
    public String toString() {
        return "GetFamilyRelationListRequest{" +
                "type='" + type + '\'' +
                ", mid='" + mid + '\'' +
                ", createTimeBegin=" + createTimeBegin +
                ", createTimeEnd=" + createTimeEnd +
                '}';
    }
}
