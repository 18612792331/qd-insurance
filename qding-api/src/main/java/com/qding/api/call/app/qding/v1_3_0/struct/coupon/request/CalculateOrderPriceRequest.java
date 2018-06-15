package com.qding.api.call.app.qding.v1_3_0.struct.coupon.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.oder.dto.OrderGeneratorDto;

/**
 * Created by Administrator on 2015/8/13.
 */
public class CalculateOrderPriceRequest extends BaseRequest {


    private static final long serialVersionUID = 8773219252687439587L;

    private OrderGeneratorDto orderGeneratorDto;

    public OrderGeneratorDto getOrderGeneratorDto() {
        return orderGeneratorDto;
    }

    public void setOrderGeneratorDto(OrderGeneratorDto orderGeneratorDto) {
        this.orderGeneratorDto = orderGeneratorDto;
    }
}
