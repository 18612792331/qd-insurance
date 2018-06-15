package com.qding.api.call.app.qding.v2_3_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderGoods;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.RedeemCode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qd on 2016/5/18.
 */
public class BuyGoodInfoBean extends OrderGoods implements Serializable {

    private static final long serialVersionUID = 1528997995165732379L;


    @ExplainAnnotation (explain = "市场价")
    private String originalPrice;

    @ExplainAnnotation (explain = "优惠活动")
    private String[] activityInfo;

    @ExplainAnnotation (explain = "供货商ID")
    private Long providerId;

    @ExplainAnnotation (explain = "供货商名称")
    private String providerName;

    @ExplainAnnotation(explain = "乐购产品线Code")
    private String markingCode;

    @ExplainAnnotation(explain = "乐购产品线名称")
    private String markingName;

    @ExplainAnnotation(explain = "兑换码列表")
    private List<RedeemCode> redeemCodeList;

    @ExplainAnnotation(explain = "分组时使用的配送方式",desc = "api内部使用，app不用关注 只适用于2.5及以上版本，主要针对周先生特殊性，用它来确认周先生商品是分入自提组还是物流组")
    private Integer groupTo;

    public Integer getGroupTo() {
        return groupTo;
    }

    public void setGroupTo(Integer groupTo) {
        this.groupTo = groupTo;
    }

    public List<RedeemCode> getRedeemCodeList() {
        return redeemCodeList;
    }

    public void setRedeemCodeList(List<RedeemCode> redeemCodeList) {
        this.redeemCodeList = redeemCodeList;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }


    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }


    public String[] getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(String[] activityInfo) {
        this.activityInfo = activityInfo;
    }

    public String getMarkingCode() {
        return markingCode;
    }

    public void setMarkingCode(String markingCode) {
        this.markingCode = markingCode;
    }

    public String getMarkingName() {
        return markingName;
    }

    public void setMarkingName(String markingName) {
        this.markingName = markingName;
    }

    public BuyGoodInfoBean(){

    }

    public BuyGoodInfoBean(String originalPrice, String[] activityInfo, Long providerId, String providerName) {
        this.originalPrice = originalPrice;
        this.activityInfo = activityInfo;
        this.providerId = providerId;
        this.providerName = providerName;
    }

    public BuyGoodInfoBean(String orderCode, String subOrderCode, String goodsId, String skuId, String goodsName, String[] skuImgUrl, String price, Integer buyNum, Integer deliveryType, String goodsDesc, String originalPrice, String[] activityInfo, Long providerId, String providerName) {
        super(orderCode, subOrderCode, goodsId, skuId, goodsName, skuImgUrl, price, buyNum, deliveryType, goodsDesc);
        this.originalPrice = originalPrice;
        this.activityInfo = activityInfo;
        this.providerId = providerId;
        this.providerName = providerName;
    }


    public BuyGoodInfoBean(String goodsId, String skuId, String goodsName, String[] skuImgUrl, String price, Integer buyNum, Integer deliveryType, String goodsDesc, String originalPrice, String[] activityInfo, Long providerId, String providerName) {
        super( goodsId, skuId, goodsName, skuImgUrl, price, buyNum, deliveryType, goodsDesc);
        this.originalPrice = originalPrice;
        this.activityInfo = activityInfo;
        this.providerId = providerId;
        this.providerName = providerName;
    }

    @Override
    public String toString() {
        return "BuyGoodInfoBean{" +
                "originalPrice='" + originalPrice + '\'' +
                ", activityInfo=" + Arrays.toString(activityInfo) +
                ", providerId=" + providerId +
                ", providerName='" + providerName + '\'' +
                '}';
    }
}
