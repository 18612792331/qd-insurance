package com.qding.api.call.app.qding.v3_1_1.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import static org.apache.solr.client.solrj.SolrQuery.ORDER.desc;

/**
 * Created by qd on 2017/7/18.
 */
@Validate
public class GetInvoicesByMidRequest extends BaseRequest {

    private static final long serialVersionUID = -3498133320162775327L;

    @ExplainAnnotation(explain = "发票类型",desc = "1:纸质 2:电子")
    @NotNullValidate
    private Integer invoiceType;

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    @Override
    public String toString() {
        return "GetInvoicesByMidRequest{" +
                "invoiceType=" + invoiceType +
                '}';
    }
}
