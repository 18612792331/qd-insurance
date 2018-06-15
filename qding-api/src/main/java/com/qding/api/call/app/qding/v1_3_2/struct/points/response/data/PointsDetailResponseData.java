package com.qding.api.call.app.qding.v1_3_2.struct.points.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.points.PointsDetail;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by jiawenzheng on 2015/8/13.
 */
public class PointsDetailResponseData extends ResponseData {

    private static final long serialVersionUID = 8497339749208500401L;

    /**
     * 记录总数
     */
    private Integer totalCount;

    /**
     * 积分明细
     */
    private List<PointsDetail> list;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<PointsDetail> getList() {
        return list;
    }

    public void setList(List<PointsDetail> list) {
        this.list = list;
    }
}
