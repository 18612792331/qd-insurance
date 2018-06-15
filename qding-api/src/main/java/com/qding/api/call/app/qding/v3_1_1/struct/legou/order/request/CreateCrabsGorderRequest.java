package com.qding.api.call.app.qding.v3_1_1.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.legou.bean.LegouCrabsReceiverBean;

import java.util.List;

/**
 * Created by qd on 2017/7/17.
 */
@Validate
public class CreateCrabsGorderRequest extends BaseRequest {

    private static final long serialVersionUID = 8346016041059992145L;

    @ExplainAnnotation (explain = "微信下单ID")
    private Long publicsId;

    @NotNullValidate
    @ExplainAnnotation(explain = "订单来源")
    private Integer sourceType;

    @ExplainAnnotation(explain = "千丁券")
    private List<String> couponCodeList;

    @NotNullValidate
    @ExplainAnnotation(explain = "货品ID")
    private Long skuId;

    @NotNullValidate
    @ExplainAnnotation(explain = "货品名称")
    private String skuName;

    @NotNullValidate
    @ExplainAnnotation(explain = "购买总数")
    private Integer wareCount;

    @ExplainAnnotation(explain = "发票类型")
    private String invoiceType;

    @ExplainAnnotation(explain = "发票抬头")
    private String invoiceTitle;

    @NotNullValidate
    @ExplainAnnotation(explain = "收货相关信息")
    private List<LegouCrabsReceiverBean> receiverInfos;

    @ExplainAnnotation(explain = "请求来源")
    private String spm;

    public Long getPublicsId() {
        return publicsId;
    }

    public void setPublicsId(Long publicsId) {
        this.publicsId = publicsId;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public List<String> getCouponCodeList() {
        return couponCodeList;
    }

    public void setCouponCodeList(List<String> couponCodeList) {
        this.couponCodeList = couponCodeList;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getWareCount() {
        return wareCount;
    }

    public void setWareCount(Integer wareCount) {
        this.wareCount = wareCount;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public List<LegouCrabsReceiverBean> getReceiverInfos() {
        return receiverInfos;
    }

    public void setReceiverInfos(List<LegouCrabsReceiverBean> receiverInfos) {
        this.receiverInfos = receiverInfos;
    }

    public String getSpm() {
        return spm;
    }

    public void setSpm(String spm) {
        this.spm = spm;
    }
}
