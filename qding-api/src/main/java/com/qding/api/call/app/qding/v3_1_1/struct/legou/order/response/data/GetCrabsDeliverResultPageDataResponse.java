package com.qding.api.call.app.qding.v3_1_1.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.LegouCrabsDelivery;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/7/17.
 */
public class GetCrabsDeliverResultPageDataResponse extends ResponseData {

    private static final long serialVersionUID = 6955714078424938553L;

    @ExplainAnnotation (explain = "购买记录")
    private List<LegouCrabsDelivery> list;

    @ExplainAnnotation (explain = "总记录数")
    private Integer totalCount;

    public List<LegouCrabsDelivery> getList() {
        return list;
    }

    public void setList(List<LegouCrabsDelivery> list) {
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "GetCrabsDeliverResultPageDataResponse{" +
                "list=" + list +
                ", totalCount=" + totalCount +
                '}';
    }
}
