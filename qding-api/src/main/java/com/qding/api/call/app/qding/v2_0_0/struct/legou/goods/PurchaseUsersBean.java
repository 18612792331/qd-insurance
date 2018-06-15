package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods;


import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/12/22.
 */
public class PurchaseUsersBean implements Serializable {

    private static final long serialVersionUID = 3385780697400821183L;

    @ExplainAnnotation(explain = "总记录数")
    private int totalCount;

    @ExplainAnnotation(explain = "购买列表")
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
}
