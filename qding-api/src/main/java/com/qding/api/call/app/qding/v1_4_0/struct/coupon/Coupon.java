package com.qding.api.call.app.qding.v1_4_0.struct.coupon;

import java.io.Serializable;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

public class Coupon extends SkipUrl implements Serializable{


	private static final long serialVersionUID = -3258142233519609181L;

	@ExplainAnnotation (explain = "券号")
	private String code;

	@ExplainAnnotation (explain = "状态")
	private Integer status;

	@ExplainAnnotation (explain = "金额")
    private String price;

	@ExplainAnnotation (explain = "有效起始日期")
    private Long startTime;

	@ExplainAnnotation (explain = "有效截止日期")
    private Long endTime;

	@ExplainAnnotation (explain = "批次ID")
    private String batchId;

	@ExplainAnnotation (explain = "批次名称")
    private String batchName;

	@ExplainAnnotation (explain = "业态名称")
    private String[] productNoNames;

	@ExplainAnnotation (explain = "优惠券模板类型")
  	private Integer couponTemplateType;

	@ExplainAnnotation (explain = "是否互斥")
	private Integer isExclusive;

	@ExplainAnnotation (explain = "适用的社区名称")
	private List<String> projectName;

	@ExplainAnnotation (explain = "是否全社区通用")
	private Integer isAllProject;

	@ExplainAnnotation (explain = "券类型",desc = "（1，2:千丁券,3:物业券 4: 折扣券")
	private Integer templateType;

	@ExplainAnnotation (explain = "券标题")
	private String note;

	@ExplainAnnotation (explain = "全描述")
	private String desc;
	
	@ExplainAnnotation (explain = "订单中展示优惠券无法使用的原因",desc="3.2新增")
	private List<String> unavailableReason;
  	
    public Coupon() {

    }

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getIsAllProject() {
		return isAllProject;
	}

	public void setIsAllProject(Integer isAllProject) {
		this.isAllProject = isAllProject;
	}

	public Integer getIsExclusive() {
		return isExclusive;
	}

	public void setIsExclusive(Integer isExclusive) {
		this.isExclusive = isExclusive;
	}

	public Integer getCouponTemplateType() {
		return couponTemplateType;
	}

	public void setCouponTemplateType(Integer couponTemplateType) {
		this.couponTemplateType = couponTemplateType;
	}

	public String[] getProductNoNames() {
		return productNoNames;
	}
    
    public void setProductNoNames(String[] productNoNames) {
		this.productNoNames = productNoNames;
	}
    
	public String getBatchId() {
		return batchId;
	}


	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}


	public String getBatchName() {
		return batchName;
	}


	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public List<String> getProjectName() {
		return projectName;
	}

	public void setProjectName(List<String> projectName) {
		this.projectName = projectName;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<String> getUnavailableReason() {
		return unavailableReason;
	}

	public void setUnavailableReason(List<String> unavailableReason) {
		this.unavailableReason = unavailableReason;
	}
	
	
	
	
}
