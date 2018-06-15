package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.PurchaseUser;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/12/22.
 */
public class PurchaseUserResponseData extends ResponseData {

    private static final long serialVersionUID = -1572483965980443125L;

    @ExplainAnnotation(explain = "总记录数")
    private int totalCount;

    @ExplainAnnotation(explain = "购买此商品人列表")
    private List<PurchaseUser> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<PurchaseUser> getList() {
        return list;
    }

    public void setList(List<PurchaseUser> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PurchaseUserResponseData [list=" + list
                + ",totalCount="+totalCount+", toString()="+ super.toString() + "]";
    }

}
