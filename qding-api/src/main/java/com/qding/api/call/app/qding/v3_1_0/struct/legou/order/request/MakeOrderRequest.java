package com.qding.api.call.app.qding.v3_1_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.Arrays;
import java.util.List;

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

	@ExplainAnnotation(explain = "物业自提地址ID",desc = "每日鲜时这里可以是每日鲜自提地址或物业自提地址")
	private String projectAddressId;

	@ExplainAnnotation(explain = "快递配送地址ID")
	private String deliveryAddressId;

	@NotNullValidate
	@ExplainAnnotation(explain = "是否是每日鲜",desc = "1:是,0:不是")
	private Integer isMrx;

	@ExplainAnnotation(explain = "每日鲜配送方式",desc = "1:配送上门,2:定点自提")
	private Integer mrxDeliveryType = 0;

	@ExplainAnnotation(explain = "发票类型",desc = "1:纸质 2:电子")
	private String invoiceType;

	@ExplainAnnotation(explain = "发票抬头")
	private String invoiceTitle;

	@ExplainAnnotation (explain = "纳税人识别号",desc = "只针对公司发票")
	private String invoiceTaxId;

	@ExplainAnnotation (explain = "发票内容")
	private String invoiceContent;

	@ExplainAnnotation (explain = "电子邮箱",desc = "只针对电子发票")
	private String invoiceReceiverEmail;

	@ExplainAnnotation (explain = "开票类型",desc = "1:个人 2:单位")
	private Integer objType;

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


	public MakeOrderRequest() {

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

	public String getSpm() {
		return spm;
	}

	public void setSpm(String spm) {
		this.spm = spm;
	}

	public Integer getMrxDeliveryType() {
		return mrxDeliveryType;
	}

	public void setMrxDeliveryType(Integer mrxDeliveryType) {
		this.mrxDeliveryType = mrxDeliveryType;
	}

	public Integer getIsMrx() {
		return isMrx;
	}

	public void setIsMrx(Integer isMrx) {
		this.isMrx = isMrx;
	}

	public Integer getObjType() {
		return objType;
	}

	public void setObjType(Integer objType) {
		this.objType = objType;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceTaxId() {
		return invoiceTaxId;
	}

	public void setInvoiceTaxId(String invoiceTaxId) {
		this.invoiceTaxId = invoiceTaxId;
	}

	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public String getInvoiceReceiverEmail() {
		return invoiceReceiverEmail;
	}

	public void setInvoiceReceiverEmail(String invoiceReceiverEmail) {
		this.invoiceReceiverEmail = invoiceReceiverEmail;
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
				", isMrx=" + isMrx +
				", mrxDeliveryType=" + mrxDeliveryType +
				", invoiceType='" + invoiceType + '\'' +
				", invoiceTitle='" + invoiceTitle + '\'' +
				", invoiceTaxId='" + invoiceTaxId + '\'' +
				", invoiceContent='" + invoiceContent + '\'' +
				", invoiceReceiverEmail='" + invoiceReceiverEmail + '\'' +
				", objType=" + objType +
				", isAnonymity=" + isAnonymity +
				", orderPromotionIds=" + orderPromotionIds +
				", remarks='" + remarks + '\'' +
				", spm='" + spm + '\'' +
				", hkMid='" + hkMid + '\'' +
				'}';
	}
}
