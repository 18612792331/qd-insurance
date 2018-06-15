package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.struct.ResponseData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetGoodsByProjectIdResponse extends ResponseData {

    private static final long serialVersionUID = 1736933140579101182L;

    @ExplainAnnotation(explain = "商品信息对象列表")
    @Setter
    @Getter
    private List<Goods> list;


}
