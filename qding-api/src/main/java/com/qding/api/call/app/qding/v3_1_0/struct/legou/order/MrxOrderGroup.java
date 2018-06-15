package com.qding.api.call.app.qding.v3_1_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrdersGroupBySupplierBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/6/5.
 */
public class MrxOrderGroup implements Serializable {


    private static final long serialVersionUID = 2632383763693941740L;

    @ExplainAnnotation (explain = "是否有匹配的配送地址",desc = "用于获取联系人 0：无地址,1：无匹配地址，2:有单个匹配地址，3:多个匹配地址")
    private Integer isHaveDeliveryAddress;

    @ExplainAnnotation (explain = "物业地址联系方式")
    private ProjectConcat projectConcat;

    @ExplainAnnotation (explain = "是否开启配送上门(每日鲜)",desc = " 1:开启,0：未开启")
    private Integer isSupportDistribute;

    @ExplainAnnotation (explain = "收货方式",desc = " 1:配送上门,2:物业自提")
    private Integer deliveryType;

    @ExplainAnnotation(explain = "订单商品安供货商分组列表")
    private List<OrdersGroupBySupplierBean> list;

    public Integer getIsHaveDeliveryAddress() {
        return isHaveDeliveryAddress;
    }

    public void setIsHaveDeliveryAddress(Integer isHaveDeliveryAddress) {
        this.isHaveDeliveryAddress = isHaveDeliveryAddress;
    }

    public ProjectConcat getProjectConcat() {
        return projectConcat;
    }

    public void setProjectConcat(ProjectConcat projectConcat) {
        this.projectConcat = projectConcat;
    }

    public Integer getIsSupportDistribute() {
        return isSupportDistribute;
    }

    public void setIsSupportDistribute(Integer isSupportDistribute) {
        this.isSupportDistribute = isSupportDistribute;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public List<OrdersGroupBySupplierBean> getList() {
        return list;
    }

    public void setList(List<OrdersGroupBySupplierBean> list) {
        this.list = list;
    }
}
