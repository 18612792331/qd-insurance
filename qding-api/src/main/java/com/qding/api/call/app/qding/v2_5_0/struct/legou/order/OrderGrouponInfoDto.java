package com.qding.api.call.app.qding.v2_5_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * 订单团购信息
 * Created by jinhaishan on 17/4/19.
 */
public class OrderGrouponInfoDto implements Serializable {

    private static final long serialVersionUID = 1012360575496957835L;

//    private Long id;
//    private String orderCode;
//    private String originalCode;
//    private String subOrderCode;
//    private Long grouponId;
//    private String grouponName;
//    private String discount;
//    private String integralAmount;
//    private String predepositAmount;
//    private Integer grouponStatus = Integer.valueOf(1);
//    private Long grouponEndTime;
//    private Integer status = Integer.valueOf(0);
//    private Long createAt;
//
//
//    private String statusName;
//    private String couponAmount;
//    private String totalRealpay;

    @ExplainAnnotation(explain = "团购状态名称")
    private String grouponStatusName;
    
    @ExplainAnnotation(explain = "团购状态", desc="1.未开团   2.团购进行中   3.已成团   4.团购失败")
    private Integer grouponStatus;

    @ExplainAnnotation(explain = "实付金额", desc = "团购最终成交价")
    private String totalRealpay;


    @ExplainAnnotation(explain = "赠送千丁券")
    private String couponAmount;

    @ExplainAnnotation(explain = "返还金额")
    private String totalDiscount;

    @ExplainAnnotation(explain = "返积分")
    private String integralAmount;

    @ExplainAnnotation(explain = "折扣额", desc = "参加阶梯团购优惠的金额")
    private String discount;


    public String getGrouponStatusName() {
        return grouponStatusName;
    }

    public void setGrouponStatusName(String grouponStatusName) {
        this.grouponStatusName = grouponStatusName;
    }

    public String getTotalRealpay() {
        return totalRealpay;
    }

    public void setTotalRealpay(String totalRealpay) {
        this.totalRealpay = totalRealpay;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getIntegralAmount() {
        return integralAmount;
    }

    public void setIntegralAmount(String integralAmount) {
        this.integralAmount = integralAmount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

	public Integer getGrouponStatus() {
		return grouponStatus;
	}

	public void setGrouponStatus(Integer grouponStatus) {
		this.grouponStatus = grouponStatus;
	}
    
    
    
}
