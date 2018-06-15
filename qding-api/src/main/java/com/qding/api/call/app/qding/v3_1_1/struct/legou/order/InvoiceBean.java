package com.qding.api.call.app.qding.v3_1_1.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/7/18.
 */
public class InvoiceBean implements Serializable {

    private static final long serialVersionUID = -1746291639315495530L;

    @ExplainAnnotation(explain = "发票类型",desc = "1:纸质 2:电子")
    private String invoiceType;

    @ExplainAnnotation(explain = "发票抬头")
    private String invoiceTitle;

    @ExplainAnnotation (explain = "纳税人识别号",desc = "只针对公司发票")
    private String invoiceTaxId;

    @ExplainAnnotation (explain = "发票内容")
    private String invoiceContent;

    @ExplainAnnotation (explain = "电子邮箱",desc = "只针对电子发票")
    private String invoiceReceiverEmail;

    @ExplainAnnotation (explain = "开票类型",desc = "1:个人 2:单位")
    private Integer objType;

    @ExplainAnnotation (explain = "默认发票",desc = "1:是 0:否")
    private Integer isDefault;

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

    public String getInvoiceTaxId() {
        return invoiceTaxId;
    }

    public void setInvoiceTaxId(String invoiceTaxId) {
        this.invoiceTaxId = invoiceTaxId;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getInvoiceReceiverEmail() {
        return invoiceReceiverEmail;
    }

    public void setInvoiceReceiverEmail(String invoiceReceiverEmail) {
        this.invoiceReceiverEmail = invoiceReceiverEmail;
    }

    public Integer getObjType() {
        return objType;
    }

    public void setObjType(Integer objType) {
        this.objType = objType;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
