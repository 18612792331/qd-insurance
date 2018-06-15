package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;


@XStreamAlias(value = "propertyBillsInfo")
public class PropertyBillsInfo implements Serializable {

    private static final long serialVersionUID = -2956761655759771703L;

    @ExplainAnnotation(explain = "年份")
    private String year;

    @ExplainAnnotation(explain = "账单列表")
    private List<PropertyBills> billsList;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<PropertyBills> getBillsList() {
        return billsList;
    }

    public void setBillsList(List<PropertyBills> billsList) {
        this.billsList = billsList;
    }
}
