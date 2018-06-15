package com.qding.api.call.app.qding.v1_3_2.struct.popularize;

import java.io.Serializable;

/**
 * Created by qd on 2015/11/23.
 */
public class PopularizeStatistics implements Serializable {

    /**
     * 累积推广用户数
     */
    private Integer popularizeUserCount = 0;

    /**
     * 累积推广订单数
     */
    private Integer popularizeOrderCount = 0;

    /**
     * 累积推广退单数
     */
    private Integer popularizeRefundCount = 0;

    public Integer getPopularizeUserCount() {
        return popularizeUserCount;
    }

    public void setPopularizeUserCount(Integer popularizeUserCount) {
        this.popularizeUserCount = popularizeUserCount;
    }

    public Integer getPopularizeOrderCount() {
        return popularizeOrderCount;
    }

    public void setPopularizeOrderCount(Integer popularizeOrderCount) {
        this.popularizeOrderCount = popularizeOrderCount;
    }

    public Integer getPopularizeRefundCount() {
        return popularizeRefundCount;
    }

    public void setPopularizeRefundCount(Integer popularizeRefundCount) {
        this.popularizeRefundCount = popularizeRefundCount;
    }
}
