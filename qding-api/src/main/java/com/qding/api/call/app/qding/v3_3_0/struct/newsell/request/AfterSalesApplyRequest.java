package com.qding.api.call.app.qding.v3_3_0.struct.newsell.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import lombok.Getter;
import lombok.Setter;

@Validate
public class AfterSalesApplyRequest extends BaseRequest {

    private static final long serialVersionUID = -5014552553048220601L;


    @Getter
    @Setter
    @NotNullValidate
    @ExplainAnnotation(explain = "申请售后的千丁订单")
    private String qdOrderCode;

    @Getter
    @Setter
    @NotNullValidate
    @ExplainAnnotation(explain = "申请售后的千丁子订单")
    private String qdSubOrderCode;

    @Getter
    @Setter
    @NotNullValidate
    @ExplainAnnotation(explain = "申请售后的第三方订单")
    private String thirdOrderCode;

    @Getter
    @Setter
    @NotNullValidate
    @ExplainAnnotation(explain = "申请原因")
    private String reason;

    @Getter
    @Setter
    @NotNullValidate
    @ExplainAnnotation(explain = "售后类型",desc = "1:退货 2:换货 3:维修")
    private Integer applyType;

    @Getter
    @Setter
    @ExplainAnnotation(explain = "申请售后备注")
    private String remark;

    @Getter
    @Setter
    @NotNullValidate
    @ExplainAnnotation(explain = "第三方渠道类型 1：点滴")
    private Integer sourceType;

    @Getter
    @Setter
    @NotNullValidate
    @ExplainAnnotation(explain = "第三方售后申请结构体 (json格式针对不同的渠道商，结构不一致，所以在接入新渠道的时候需要沟通)")
    private String structure;
    
    @Getter
    @Setter
    @ExplainAnnotation(explain = "图片URL")
    private String imgs;

    @Override
    public String toString() {
        return "AfterSalesApplyRequest{" +
                "qdOrderCode='" + qdOrderCode + '\'' +
                ", qdSubOrderCode='" + qdSubOrderCode + '\'' +
                ", thirdOrderCode='" + thirdOrderCode + '\'' +
                ", reason='" + reason + '\'' +
                ", applyType=" + applyType +
                ", remark='" + remark + '\'' +
                ", sourceType=" + sourceType +
                ", structure='" + structure + '\'' +
                ", imgs='" + imgs + '\'' +
                '}';
    }
}
