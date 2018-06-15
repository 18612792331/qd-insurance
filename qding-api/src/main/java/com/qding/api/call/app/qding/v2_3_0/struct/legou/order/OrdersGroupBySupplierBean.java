package com.qding.api.call.app.qding.v2_3_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/5/18.
 */
public class OrdersGroupBySupplierBean implements Serializable {

    private static final long serialVersionUID = -481936180402850963L;

    @ExplainAnnotation(explain = "供货商名称")
    private String supplierName;

    @ExplainAnnotation (explain = "商品信息列表")
    private List<BuyGoodInfoBean> goodInfoBeanList;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<BuyGoodInfoBean> getGoodInfoBeanList() {
        return goodInfoBeanList;
    }

    public void setGoodInfoBeanList(List<BuyGoodInfoBean> goodInfoBeanList) {
        this.goodInfoBeanList = goodInfoBeanList;
    }
}
