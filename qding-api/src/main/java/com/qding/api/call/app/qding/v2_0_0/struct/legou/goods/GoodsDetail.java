package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.SpeRel;

import java.util.List;

public class GoodsDetail extends Goods {
	
	private static final long serialVersionUID = 5976974093875045508L;

	
	public GoodsDetail(){
		
	}

	@ExplainAnnotation(explain = "库存")
	private int count;

	@ExplainAnnotation(explain = "是否为有限库存",desc = "1:无限库存，0：有限库存")
	private int unlimitedStock;
	
	@ExplainAnnotation(explain = "状态")
	private String status;
	
	@ExplainAnnotation(explain = "是否加入购物车")
	private String isCollect = "0";
	
	@ExplainAnnotation(explain = "运费")
	private String postage = "0";
	
	@ExplainAnnotation(explain = "购买须知")
	private String comment="";
	
	@ExplainAnnotation(explain = "剩余时间")
	private Long discountTimeLeft;
	
	@ExplainAnnotation(explain = "每单购买限制",desc = "-1:没有限制")
	private int limitNum;
	
	@ExplainAnnotation(explain = "规格")
	private String[] spe;
	
	@ExplainAnnotation(explain = "规格属性")
	private String[] speInfo;
	
	@ExplainAnnotation(explain = "相同规格关联商品")
	private List<SpeRel> specRel;
	
	@ExplainAnnotation(explain = "商品说明详情")
	private String desc;
	
	@ExplainAnnotation(explain = "货品已售数量")
	private Long countSkuSellNum = 0L;
	
	@ExplainAnnotation(explain = "商品已售数量")
	private Long countWareSellNum = 0L;
	
	@ExplainAnnotation(explain = "是否送货上门或自提",desc = " 1：上门  2：自取")
	private Integer deliveryType =1;

	@ExplainAnnotation(explain = "是否支持购物车",desc = "0：不支持 1：支持")
	private Integer supportCart = 1;

	@ExplainAnnotation(explain = "已购买用户信息列表")
	private PurchaseUsersBean purchaseUser;

	public PurchaseUsersBean getPurchaseUser() {
		return purchaseUser;
	}

	public void setPurchaseUser(PurchaseUsersBean purchaseUser) {
		this.purchaseUser = purchaseUser;
	}

	public Integer getSupportCart() {
		return supportCart;
	}

	public void setSupportCart(Integer supportCart) {
		this.supportCart = supportCart;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}


	/**
	 * @return the deliveryType
	 */
	public Integer getDeliveryType() {
		return deliveryType;
	}

	/**
	 * @param deliveryType the deliveryType to set
	 */
	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Long getCountSkuSellNum() {
		return countSkuSellNum;
	}

	public void setCountSkuSellNum(Long countSkuSellNum) {
		this.countSkuSellNum = countSkuSellNum;
	}

	public Long getCountWareSellNum() {
		return countWareSellNum;
	}

	public void setCountWareSellNum(Long countWareSellNum) {
		this.countWareSellNum = countWareSellNum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}

	public String getPostage() {
		return postage;
	}

	public void setPostage(String postage) {
		this.postage = postage;
	}

/*	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}*/

	public Long getDiscountTimeLeft() {
		return discountTimeLeft;
	}

	public void setDiscountTimeLeft(Long discountTimeLeft) {
		this.discountTimeLeft = discountTimeLeft;
	}

	public int getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}

	public String[] getSpe() {
		return spe;
	}

	public void setSpe(String[] spe) {
		this.spe = spe;
	}

	public String[] getSpeInfo() {
		return speInfo;
	}

	public void setSpeInfo(String[] speInfo) {
		this.speInfo = speInfo;
	}

	public List<SpeRel> getSpecRel() {
		return specRel;
	}

	public void setSpecRel(List<SpeRel> specRel) {
		this.specRel = specRel;
	}
	
	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getUnlimitedStock() {
		return unlimitedStock;
	}

	public void setUnlimitedStock(int unlimitedStock) {
		this.unlimitedStock = unlimitedStock;
	}
}
