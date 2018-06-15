package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.struct.ResponseData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetGoodsByCategoryIdResponseData extends ResponseData {

    private static final long serialVersionUID = -1121432492009531498L;

    @ExplainAnnotation(explain = "总记录数")
    @Setter
    @Getter
    private int totalCount;

    @ExplainAnnotation(explain = "商品信息对象列表")
    @Setter
    @Getter
    private List<Goods> list;

    @Override
    public String toString() {
        return "GetGoodsByCategoryIdResponseData{" +
                "totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }
}
