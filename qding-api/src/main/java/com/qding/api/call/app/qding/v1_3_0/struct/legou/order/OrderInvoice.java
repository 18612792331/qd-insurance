package com.qding.api.call.app.qding.v1_3_0.struct.legou.order;

import java.io.Serializable;

public class OrderInvoice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5365108814750732195L;

	/**
     * 发票类型
     */
    private String invoiceType;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    public OrderInvoice() {

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
    
    
}
