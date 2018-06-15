package com.qding.api.call.app.qding.v3_1_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by qd on 2017/5/12.
 */
@Validate
public class ConfirmOrderRequest extends BaseRequest{

    private static final long serialVersionUID = 5664144199362371199L;

    @NotNullValidate
    @ExplainAnnotation (explain = "显示类型",desc = "1:每日鲜，2：通用订单")
    private Integer  showType;

    @NotNullValidate
    @ExplainAnnotation (explain = "订单来源")
    private Integer orderSourceType;

    @ExplainAnnotation (explain = "可用千丁券")
    private String[] couponCodes;

    @ExplainAnnotation (explain = "订单促销ID")
    private List<String> orderPromotionIds;

    @ExplainAnnotation (explain = "购物车商品")
    private List<Sku> skus;

    @NotNullValidate
    @ExplainAnnotation (explain = "是否使用优惠券",desc = "0:首次调用，1：用户手动选择优惠券,2:用户不使用任何优惠券")
    private Integer isUserChooseCoupon;

    @ExplainAnnotation (explain = "用户自选快递配送地址ID")
    private String addrId;


    public ConfirmOrderRequest() {

    }

    public String getAddrId() {
        return addrId;
    }

    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }

    public Integer getIsUserChooseCoupon() {
        return isUserChooseCoupon;
    }

    public void setIsUserChooseCoupon(Integer isUserChooseCoupon) {
        this.isUserChooseCoupon = isUserChooseCoupon;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public Integer getOrderSourceType() {
        return orderSourceType;
    }

    public void setOrderSourceType(Integer orderSourceType) {
        this.orderSourceType = orderSourceType;
    }

    public String[] getCouponCodes() {
        return couponCodes;
    }

    public void setCouponCodes(String[] couponCodes) {
        this.couponCodes = couponCodes;
    }

    public List<String> getOrderPromotionIds() {
        return orderPromotionIds;
    }

    public void setOrderPromotionIds(List<String> orderPromotionIds) {
        this.orderPromotionIds = orderPromotionIds;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "ConfirmOrderRequest{" +
                "showType=" + showType +
                ", orderSourceType=" + orderSourceType +
                ", couponCodes=" + Arrays.toString(couponCodes) +
                ", orderPromotionIds=" + orderPromotionIds +
                ", skus=" + skus +
                ", isUserChooseCoupon=" + isUserChooseCoupon +
                '}';
    }
}
