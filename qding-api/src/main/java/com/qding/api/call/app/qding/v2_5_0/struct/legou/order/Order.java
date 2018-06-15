package com.qding.api.call.app.qding.v2_5_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.CouponsPromotion;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.GoodsPromotion;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.OrderPromotion;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderBase;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderInvoice;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrderGroup;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.LogisticsInfo;
import com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.SkuGrouponInfo;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    private static final long serialVersionUID = 6566766289133702835L;

    @ExplainAnnotation(explain = "订单详情")
    private OrderBase orderBase;

    @ExplainAnnotation(explain = "发票信息")
    private OrderInvoice orderInvoice;

    @ExplainAnnotation(explain = "物业地址联系方式")
    private ProjectConcat projectConcat;

    @ExplainAnnotation(explain = "收件人地址")
    private Addresses deliveryAddress;

/*	@ExplainAnnotation(explain = "送货地址")
    private OrderDelivery orderDelivery;*/

    @ExplainAnnotation(explain = "物流配送组")
    private OrderGroup logisticsDis;

    @ExplainAnnotation(explain = "物业自提组")
    private OrderGroup propertySelf;

    @ExplainAnnotation(explain = "每日鲜订单组")
    private OrderGroup mrxOrder;

    @ExplainAnnotation(explain = "订单优惠")
    private List<OrderPromotion> orderPromotiones;

    @ExplainAnnotation(explain = "商品优惠")
    private List<GoodsPromotion> goodsPromotiones;

    @ExplainAnnotation(explain = "千丁券优惠")
    private CouponsPromotion couponCodePromotion;

    @ExplainAnnotation(explain = "物流相关信息")
    private List<LogisticsInfo> logisticsInfoList;

    @ExplainAnnotation(explain = "乐购货品状态", desc = "用于判断是否可签收")
    private Integer legouStatus = 0;

    @ExplainAnnotation(explain = "支付时效提醒", desc = "eg:请于下单后48小时内完成支付")
    private String payRemind;

    @ExplainAnnotation(explain = "是否拆单标志", desc = "0:未拆 | 1：已拆")
    private Integer isSplit;

    @ExplainAnnotation(explain = "备注")
    private String remarks;

    @ExplainAnnotation(explain = "周先生配送类型", desc = "1：快递配送  2：自提 《只用于周先生商品送货地址展示时作为判断使用》")
    private Integer zxsDeliveryType;

    @ExplainAnnotation(explain = "团购信息")
    private List<OrderGrouponInfoDto> orderGrouponInfoDtos;

    @ExplainAnnotation(explain = "提货码",desc = "3.1新增 每日鲜提货码")
    private String takeCode;

    @ExplainAnnotation (explain = "显示类型",desc = "1:每日鲜订单，2：通用订单")
    private Integer  showType;

    @ExplainAnnotation (explain = "配送费")
    private String expressPrice = "0.00";
    
    public String getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(String expressPrice) {
        this.expressPrice = expressPrice;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public String getTakeCode() {
        return takeCode;
    }

    public void setTakeCode(String takeCode) {
        this.takeCode = takeCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<LogisticsInfo> getLogisticsInfoList() {
        return logisticsInfoList;
    }

    public void setLogisticsInfoList(List<LogisticsInfo> logisticsInfoList) {
        this.logisticsInfoList = logisticsInfoList;
    }

    public Integer getLegouStatus() {
        return legouStatus;
    }

    public void setLegouStatus(Integer legouStatus) {
        this.legouStatus = legouStatus;
    }

    public OrderBase getOrderBase() {
        return orderBase;
    }

    public void setOrderBase(OrderBase orderBase) {
        this.orderBase = orderBase;
    }

    public OrderInvoice getOrderInvoice() {
        return orderInvoice;
    }

    public void setOrderInvoice(OrderInvoice orderInvoice) {
        this.orderInvoice = orderInvoice;
    }

/*	public OrderDelivery getOrderDelivery() {
        return orderDelivery;
	}

	public void setOrderDelivery(OrderDelivery orderDelivery) {
		this.orderDelivery = orderDelivery;
	}*/


    public List<OrderPromotion> getOrderPromotiones() {
        return orderPromotiones;
    }

    public void setOrderPromotiones(List<OrderPromotion> orderPromotiones) {
        this.orderPromotiones = orderPromotiones;
    }

    public List<GoodsPromotion> getGoodsPromotiones() {
        return goodsPromotiones;
    }

    public void setGoodsPromotiones(List<GoodsPromotion> goodsPromotiones) {
        this.goodsPromotiones = goodsPromotiones;
    }

    public CouponsPromotion getCouponCodePromotion() {
        return couponCodePromotion;
    }

    public void setCouponCodePromotion(CouponsPromotion couponCodePromotion) {
        this.couponCodePromotion = couponCodePromotion;
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

    public ProjectConcat getProjectConcat() {
        return projectConcat;
    }

    public void setProjectConcat(ProjectConcat projectConcat) {
        this.projectConcat = projectConcat;
    }

    public Addresses getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Addresses deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPayRemind() {
        return payRemind;
    }

    public void setPayRemind(String payRemind) {
        this.payRemind = payRemind;
    }

    public Integer getIsSplit() {
        return isSplit;
    }

    public void setIsSplit(Integer isSplit) {
        this.isSplit = isSplit;
    }

    public List<OrderGrouponInfoDto> getOrderGrouponInfoDtos() {
        return orderGrouponInfoDtos;
    }

    public void setOrderGrouponInfoDtos(List<OrderGrouponInfoDto> orderGrouponInfoDtos) {
        this.orderGrouponInfoDtos = orderGrouponInfoDtos;
    }

    public OrderGroup getMrxOrder() {
        return mrxOrder;
    }

    public void setMrxOrder(OrderGroup mrxOrder) {
        this.mrxOrder = mrxOrder;
    }

    public Integer getZxsDeliveryType() {
        return zxsDeliveryType;
    }

    public void setZxsDeliveryType(Integer zxsDeliveryType) {
        this.zxsDeliveryType = zxsDeliveryType;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
