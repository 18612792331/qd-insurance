package com.qding.api.call.app.qding.v2_4_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.WareComment;

import java.util.List;

/**
 * Created by qd on 2016/6/16.
 */
public class GoodsCommentBean {

    @ExplainAnnotation(explain = "总记录数")
    private int totalCount;

    @ExplainAnnotation(explain = "评价信息列表")
    private List<WareComment> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<WareComment> getList() {
        return list;
    }

    public void setList(List<WareComment> list) {
        this.list = list;
    }
}
