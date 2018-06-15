package com.qding.api.call.app.qding.v3_2_0.struct.coupon;

import java.io.Serializable;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

public class CouponDto extends SkipUrl implements Serializable{


	private static final long serialVersionUID = -3258142233519609181L;

	@ExplainAnnotation (explain = "千丁卷标题 比如：积分活动满100减5元券")
	private String title;
	
	@ExplainAnnotation (explain = "金额")
    private String price;
	
	@ExplainAnnotation (explain = "状态")
	private Integer status;

	@ExplainAnnotation (explain = "有效起始日期")
    private Long startTime;

	@ExplainAnnotation (explain = "有效截止日期")
    private Long endTime;

	@ExplainAnnotation (explain = "规则描述")
    private String ruleDesc;

	@ExplainAnnotation (explain = "金额限制",desc = "满xx元可用")
	private Integer priceLimit;
	
	@ExplainAnnotation (explain = "订单中展示优惠券无法使用的原因")
	private List<String> unavailableReason;
	
	@ExplainAnnotation (explain = "优惠券编码")
	private String code;

	public Integer getPriceLimit() {
		return priceLimit;
	}

	public void setPriceLimit(Integer priceLimit) {
		this.priceLimit = priceLimit;
	}

	public CouponDto() {

    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	public List<String> getUnavailableReason() {
		return unavailableReason;
	}

	public void setUnavailableReason(List<String> unavailableReason) {
		this.unavailableReason = unavailableReason;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
    

	 
}
