package com.qding.api.call.app.qding.v2_3_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/5/18.
 */
public class OrderGroup implements Serializable {

    private static final long serialVersionUID = 7775769420578674258L;

    @ExplainAnnotation (explain = "是否开启配送上门(每日鲜)",desc = " 1:开启,0：未开启")
    private Integer isSupportDistribute;

    @ExplainAnnotation (explain = "收货方式",desc = " 1:配送上门,2:物业自提")
    private Integer deliveryType;

    @ExplainAnnotation(explain = "收货地址")
    private Addresses deliveryAddress;

    @ExplainAnnotation(explain = "订单商品安供货商分组列表")
    private List<OrdersGroupBySupplierBean> list;


    public Addresses getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Addresses deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrdersGroupBySupplierBean> getList() {
        return list;
    }

    public void setList(List<OrdersGroupBySupplierBean> list) {
        this.list = list;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getIsSupportDistribute() {
        return isSupportDistribute;
    }

    public void setIsSupportDistribute(Integer isSupportDistribute) {
        this.isSupportDistribute = isSupportDistribute;
    }
}
