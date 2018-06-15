package com.qding.api.call.app.qding.v2_4_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderBean;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.RedeemCodeOrderBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/8/12.
 */
public class GetRedeemCodeOrdersResponseData extends ResponseData {

    private static final long serialVersionUID = 8980593790210863737L;

    @ExplainAnnotation(explain = "当前记录数")
    private int recordCount;

    @ExplainAnnotation(explain = "总记录数")
    private int totalCount;

    @ExplainAnnotation(explain = "订单列表")
    private List<RedeemCodeOrderBean> list;

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<RedeemCodeOrderBean> getList() {
        return list;
    }

    public void setList(List<RedeemCodeOrderBean> list) {
        this.list = list;
    }


}
