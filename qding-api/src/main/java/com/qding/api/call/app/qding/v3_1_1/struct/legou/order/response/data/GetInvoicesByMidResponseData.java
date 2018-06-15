package com.qding.api.call.app.qding.v3_1_1.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.InvoiceBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/7/18.
 */
public class GetInvoicesByMidResponseData extends ResponseData {

    private static final long serialVersionUID = 2597200334302380396L;

    @ExplainAnnotation (explain = "发票列表")
    private List<InvoiceBean> invoiceBeanList;

    public List<InvoiceBean> getInvoiceBeanList() {
        return invoiceBeanList;
    }

    public void setInvoiceBeanList(List<InvoiceBean> invoiceBeanList) {
        this.invoiceBeanList = invoiceBeanList;
    }
}
