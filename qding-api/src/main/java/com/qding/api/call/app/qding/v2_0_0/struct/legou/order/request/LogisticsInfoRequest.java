package com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2016/1/7.
 */
public class LogisticsInfoRequest extends BaseRequest {

    private static final long serialVersionUID = -2311552995357767570L;

    @ExplainAnnotation(explain = "物流单号")
    private String logisticsCode;

    @ExplainAnnotation(explain = "快递公司名称")
    private String companyCode;

    @ExplainAnnotation(explain = "订单号")
    private String orderCode;

    @ExplainAnnotation(explain = "产品类型",desc = "0:其他商品，1:周鲜生,2:日鲜车")
    private Integer type;

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LogisticsInfoRequest{" +
                "logisticsCode='" + logisticsCode + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", type=" + type +
                '}';
    }
}
