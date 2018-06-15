package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/12/22.
 */
public class SearchGoodBean implements Serializable {

    @ExplainAnnotation(explain = "当前记录数")
    private Long recordCount;

    @ExplainAnnotation(explain = "总记录数")
    private Long totalCount;

    @ExplainAnnotation(explain = "商品信息列表")
    private List<Goods> list;

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }
}
