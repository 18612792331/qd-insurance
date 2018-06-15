package com.qding.api.call.app.qding.v3_2_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrderGroup;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.MrxOrderGroup;
import com.qding.api.call.app.qding.v3_2_0.struct.legou.order.ConfirmOrder;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/5/12.
 */
public class ConfirmOrderResponseData extends ResponseData {

    private static final long serialVersionUID = 8322369230818571755L;

    @ExplainAnnotation (explain = "显示类型",desc = "1:每日鲜，2：通用订单")
    private Integer  showType = 2;

    @ExplainAnnotation(explain = "订单确认页")
    private ConfirmOrder entity;

    @ExplainAnnotation(explain = "物流配送组")
    private OrderGroup logisticsDis;

    @ExplainAnnotation(explain = "物业自提组")
    private OrderGroup propertySelf;

    @ExplainAnnotation(explain = "每日鲜订单组")
    private MrxOrderGroup mrxOrder;
   

    public ConfirmOrderResponseData() {

    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public MrxOrderGroup getMrxOrder() {
        return mrxOrder;
    }

    public void setMrxOrder(MrxOrderGroup mrxOrder) {
        this.mrxOrder = mrxOrder;
    }

    public ConfirmOrder getEntity() {
        return entity;
    }

    public void setEntity(ConfirmOrder entity) {
        this.entity = entity;
    }

    public OrderGroup getLogisticsDis() {
        return logisticsDis;
    }

    public void setLogisticsDis(OrderGroup logisticsDis) {
        this.logisticsDis = logisticsDis;
    }

    public OrderGroup getPropertySelf() {
        return propertySelf;
    }

    public void setPropertySelf(OrderGroup propertySelf) {
        this.propertySelf = propertySelf;
    }

    @Override
    public String toString() {
        return "ConfirmOrderResponseData{" +
                "showType=" + showType +
                ", entity=" + entity +
                ", logisticsDis=" + logisticsDis +
                ", propertySelf=" + propertySelf +
                ", mrxOrder=" + mrxOrder +
                '}';
    }
}
