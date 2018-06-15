package com.qding.api.call.app.qding.v1_3_2.struct.popularize;

import java.io.Serializable;

/**
 * Created by qd on 2015/11/23.
 */
public class StatisticsItemsBean implements Serializable {

    /**
     * 日期
     */
    private String date;

    /**
     * 数量
     */
    private Integer count;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
