package com.qding.api.call.app.qding.v3_3_0.struct.newsell.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.BuyGoodInfoBean;
import com.qding.api.call.app.qding.v3_3_0.struct.newsell.AfterSalesSubOrderDto;
import com.qding.api.struct.ResponseData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetSubOrdersByPcodeResponseData extends ResponseData {

    private static final long serialVersionUID = -1703183355768609937L;


    @ExplainAnnotation(explain = "子订单列表")
    @Getter
    @Setter
    private List<AfterSalesSubOrderDto> list;



}
