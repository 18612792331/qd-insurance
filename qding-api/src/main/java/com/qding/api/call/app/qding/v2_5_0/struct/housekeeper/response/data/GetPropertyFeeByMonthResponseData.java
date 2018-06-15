package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.PropertyBillsByMonthInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetPropertyFeeByMonthResponseData extends ResponseData {

    private static final long serialVersionUID = 6806038544150193190L;


    @ExplainAnnotation(explain = "房屋账单列表")
    private List<PropertyBillsByMonthInfo> list;

    @ExplainAnnotation(explain = "总记录数")
    private int totalCount;

    public List<PropertyBillsByMonthInfo> getList() {
        return list;
    }

    public void setList(List<PropertyBillsByMonthInfo> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "GetPropertyFeeByMonthResponseData{" +
                "list=" + list +
                ", totalCount=" + totalCount +
                '}';
    }
}
