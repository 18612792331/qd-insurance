package com.qding.api.call.app.qding.v3_3_0.struct.newsell;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class AfterSalesSubOrderDto implements Serializable {

    private static final long serialVersionUID = 754914440333118241L;


    @ExplainAnnotation(explain = "货品ID")
    private String skuId;

    @ExplainAnnotation(explain = "货品名称")
    private String skuName;


    @ExplainAnnotation(explain = "购买数")
    private Integer buyNum;


    @ExplainAnnotation(explain = "第三方订单号")
    private String thirdOrderCode;

    @ExplainAnnotation(explain = "第三方货品编号")
    private String thirdSkuBn;


    @ExplainAnnotation(explain = "订单号")
    private String orderCode;

    @ExplainAnnotation(explain = "子订单号")
    private String subOrderCode;


    @ExplainAnnotation(explain = "商品描述")
    private String goodsDesc;

    @ExplainAnnotation(explain = "是否已经申请了售后",desc = "-1:不可申请售后,1:已申请过 0:未申请过")
    private Integer isAfterSalesApply;


    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public String getThirdOrderCode() {
        return thirdOrderCode;
    }

    public void setThirdOrderCode(String thirdOrderCode) {
        this.thirdOrderCode = thirdOrderCode;
    }

    public String getThirdSkuBn() {
        return thirdSkuBn;
    }

    public void setThirdSkuBn(String thirdSkuBn) {
        this.thirdSkuBn = thirdSkuBn;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getSubOrderCode() {
        return subOrderCode;
    }

    public void setSubOrderCode(String subOrderCode) {
        this.subOrderCode = subOrderCode;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Integer getIsAfterSalesApply() {
        return isAfterSalesApply;
    }

    public void setIsAfterSalesApply(Integer isAfterSalesApply) {
        this.isAfterSalesApply = isAfterSalesApply;
    }


}
