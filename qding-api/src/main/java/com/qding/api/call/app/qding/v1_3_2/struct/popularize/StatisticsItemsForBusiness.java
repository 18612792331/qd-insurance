package com.qding.api.call.app.qding.v1_3_2.struct.popularize;

import java.io.Serializable;

/**
 * Created by qd on 2015/11/23.
 */
public class StatisticsItemsForBusiness implements Serializable {

    private String businessName;

    private Integer count;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
