package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request;

import java.util.Arrays;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 生成订单					
 * @author lichao
 *
 */
@Validate
public class MakeOrderRequest extends BaseRequest{

	private static final long serialVersionUID = 1245550369100874065L;

	@NotNullValidate
	@ExplainAnnotation(explain = "货品列表")
	private List<Sku> skus;
	
	@NotNullValidate
	@ExplainAnnotation(explain = "订单来源")
	private Integer orderSourceType;
	
	@NotNullValidate
	@ExplainAnnotation(explain = "是否在线支付")
	private Integer isPayOnline;

	@ExplainAnnotation(explain = "千丁券")
	private String[] couponCodes;

	@ExplainAnnotation(explain = "")
	private String publicsId;

	@ExplainAnnotation(explain = "物业自提地址ID")
	private String projectAddressId;

	@ExplainAnnotation(explain = "快递配送地址ID")
	private String deliveryAddressId;

	@ExplainAnnotation(explain = "用户选每日鲜配送地址ID")
	private String mrxAddressId;

	@ExplainAnnotation(explain = "每日鲜配送类型",desc =" 1:配送上门,2:定点自提")
	private Integer mrxAddressType;

	@ExplainAnnotation(explain = "周先生配送地址ID",desc = "强升3.1后删除")
	private String zxsAddressId;

	@ExplainAnnotation(explain = "周先生配送类型",desc = "0:物业自取，1：物流配送（上门）强升3.1后删除")
	private Integer zxsAddressType;

	@ExplainAnnotation(explain = "发票抬头")
	private String invoiceTitle;

	@ExplainAnnotation(explain = "是否匿名购买",desc = "匿名:0 不匿名:1")
	private Integer isAnonymity = 1;

	@ExplainAnnotation(explain = "促销活动ID列表")
	private List<String> orderPromotionIds;

	@ExplainAnnotation(explain = "订单备注")
	private String remarks ="";

	@ExplainAnnotation(explain = "请求来源")
	private String spm;

	@ExplainAnnotation(explain = "管家memberId")
	private String hkMid;

	public String getHkMid() {
		return hkMid;
	}

	public void setHkMid(String hkMid) {
		this.hkMid = hkMid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIsAnonymity() {
		return isAnonymity;
	}

	public void setIsAnonymity(Integer isAnonymity) {
		this.isAnonymity = isAnonymity;
	}

	public void setIsPayOnline(Integer isPayOnline) {
		this.isPayOnline = isPayOnline;
	}
	
	public Integer getIsPayOnline() {
		return isPayOnline;
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

	public String getPublicsId() {
		return publicsId;
	}

	public void setPublicsId(String publicsId) {
		this.publicsId = publicsId;
	}

	public String getProjectAddressId() {
		return projectAddressId;
	}

	public void setProjectAddressId(String projectAddressId) {
		this.projectAddressId = projectAddressId;
	}

	public String getDeliveryAddressId() {
		return deliveryAddressId;
	}

	public void setDeliveryAddressId(String deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public List<String> getOrderPromotionIds() {
		return orderPromotionIds;
	}

	public void setOrderPromotionIds(List<String> orderPromotionIds) {
		this.orderPromotionIds = orderPromotionIds;
	}

	public Integer getZxsAddressType() {
		return zxsAddressType;
	}

	public void setZxsAddressType(Integer zxsAddressType) {
		this.zxsAddressType = zxsAddressType;
	}

	public MakeOrderRequest() {

	}

	public String getZxsAddressId() {
		return zxsAddressId;
	}

	public void setZxsAddressId(String zxsAddressId) {
		this.zxsAddressId = zxsAddressId;
	}

	public String getSpm() {
		return spm;
	}

	public void setSpm(String spm) {
		this.spm = spm;
	}

	public String getMrxAddressId() {
		return mrxAddressId;
	}

	public void setMrxAddressId(String mrxAddressId) {
		this.mrxAddressId = mrxAddressId;
	}

	public Integer getMrxAddressType() {
		return mrxAddressType;
	}

	public void setMrxAddressType(Integer mrxAddressType) {
		this.mrxAddressType = mrxAddressType;
	}

	@Override
	public String toString() {
		return "MakeOrderRequest{" +
				"skus=" + skus +
				", orderSourceType=" + orderSourceType +
				", isPayOnline=" + isPayOnline +
				", couponCodes=" + Arrays.toString(couponCodes) +
				", publicsId='" + publicsId + '\'' +
				", projectAddressId='" + projectAddressId + '\'' +
				", deliveryAddressId='" + deliveryAddressId + '\'' +
				", mrxAddressId='" + mrxAddressId + '\'' +
				", mrxAddressType=" + mrxAddressType +
				", zxsAddressId='" + zxsAddressId + '\'' +
				", zxsAddressType=" + zxsAddressType +
				", invoiceTitle='" + invoiceTitle + '\'' +
				", isAnonymity=" + isAnonymity +
				", orderPromotionIds=" + orderPromotionIds +
				", remarks='" + remarks + '\'' +
				", spm='" + spm + '\'' +
				", hkMid='" + hkMid + '\'' +
				'}';
	}
}
