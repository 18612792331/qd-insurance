package com.qding.api.call.app.qding.v3_1_1.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

/**
 * Created by qd on 2017/7/17.
 */
public class LegouCrabsDelivery {

    @ExplainAnnotation (explain = "订单号")
    private String orderCode = "";

    @ExplainAnnotation (explain = "货品ID")
    private Long skuId;

    @ExplainAnnotation (explain = "货品名称")
    private String skuName = "";

    @ExplainAnnotation (explain = "货品图片")
    private String skuImgUrl = "";

    @ExplainAnnotation (explain = "货品价格")
    private String skuPrice = "";

    @ExplainAnnotation (explain = "收货人")
    private String receiverName = "";

    @ExplainAnnotation (explain = "收货人手机号")
    private String receiverPhone = "";

    @ExplainAnnotation (explain = "收货人地址")
    private String receiverAddress = "";

    @ExplainAnnotation (explain = "购买时间")
    private Long payAt;

    @ExplainAnnotation (explain = "购买数量")
    private Integer skuCouponNum;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuImgUrl() {
        return skuImgUrl;
    }

    public void setSkuImgUrl(String skuImgUrl) {
        this.skuImgUrl = skuImgUrl;
    }

    public String getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }


    public Long getPayAt() {
        return payAt;
    }

    public void setPayAt(Long payAt) {
        this.payAt = payAt;
    }

    public Integer getSkuCouponNum() {
        return skuCouponNum;
    }

    public void setSkuCouponNum(Integer skuCouponNum) {
        this.skuCouponNum = skuCouponNum;
    }

    @Override
    public String toString() {
        return "LegouCrabsDelivery{" +
                "orderCode='" + orderCode + '\'' +
                ", skuId=" + skuId +
                ", skuName='" + skuName + '\'' +
                ", skuImgUrl='" + skuImgUrl + '\'' +
                ", skuPrice='" + skuPrice + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", payAt=" + payAt +
                ", skuCouponNum=" + skuCouponNum +
                '}';
    }
}
