package com.qding.api.call.app.qding.v2_3_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;


/**
 * Created by qd on 2016/5/19.
 */
public class GetPromotionInfoForCartRequest extends BaseRequest {

    private static final long serialVersionUID = -116364873597405780L;

    @MemberValidate
    @ExplainAnnotation (explain = "会员ID")
    private String memberId;

    @NotNullValidate
    @ExplainAnnotation (explain = "社区ID")
    private String projectId;

    @NotNullValidate
    @ExplainAnnotation (explain = "货品集合")
    private List<Sku> skus;

    @NotNullValidate
    @ExplainAnnotation (explain = "订单来源")
    private Integer orderSourceType;

    @ExplainAnnotation (explain = "优惠券集合")
    private String[] couponCodes;

    @ExplainAnnotation (explain = "是否匿名购买",desc = "匿名：0 不匿名：1")
    private Integer isAnonymity = 1;

    @ExplainAnnotation (explain = "订单促销ID")
    private List<String> orderPromotionIds;


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
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

    public Integer getIsAnonymity() {
        return isAnonymity;
    }

    public void setIsAnonymity(Integer isAnonymity) {
        this.isAnonymity = isAnonymity;
    }

    public List<String> getOrderPromotionIds() {
        return orderPromotionIds;
    }

    public void setOrderPromotionIds(List<String> orderPromotionIds) {
        this.orderPromotionIds = orderPromotionIds;
    }

    public GetPromotionInfoForCartRequest(String memberId, String projectId, List<Sku> skus, Integer orderSourceType, String[] couponCodes, Integer isAnonymity, List<String> orderPromotionIds) {
        this.memberId = memberId;
        this.projectId = projectId;
        this.skus = skus;
        this.orderSourceType = orderSourceType;
        this.couponCodes = couponCodes;
        this.isAnonymity = isAnonymity;
        this.orderPromotionIds = orderPromotionIds;
    }

    public GetPromotionInfoForCartRequest() {
    }
}
