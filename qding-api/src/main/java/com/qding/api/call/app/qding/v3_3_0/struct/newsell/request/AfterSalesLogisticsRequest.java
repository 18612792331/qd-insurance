package com.qding.api.call.app.qding.v3_3_0.struct.newsell.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import lombok.Getter;
import lombok.Setter;

@Validate
public class AfterSalesLogisticsRequest extends BaseRequest {

    private static final long serialVersionUID = -7414356048606532885L;

    /**
     * 售后申请ID
     */
    @Getter
    @Setter
    @NotNullValidate
    private String afterSalesApplyId;


    /**
     * 千丁物流公司ID
     */
    @Getter
    @Setter
    @NotNullValidate
    private Long qdCompanyId;

    /**
     * 千丁物流公司名称
     */
    @Getter
    @Setter
    @NotNullValidate
    private String qdCompany;


    /**
     * 物流订单号
     */
    @Getter
    @Setter
    @NotNullValidate
    private String logisticsCode;


    /**
     * 渠道类型 1：点滴
     */
    @Getter
    @Setter
    @NotNullValidate
    private Integer sourceType;


    @Override
    public String toString() {
        return "AfterSalesLogisticsRequest{" +
                "afterSalesApplyId='" + afterSalesApplyId + '\'' +
                ", qdCompanyId=" + qdCompanyId +
                ", qdCompany='" + qdCompany + '\'' +
                ", logisticsCode='" + logisticsCode + '\'' +
                ", sourceType=" + sourceType +
                '}';
    }
}
