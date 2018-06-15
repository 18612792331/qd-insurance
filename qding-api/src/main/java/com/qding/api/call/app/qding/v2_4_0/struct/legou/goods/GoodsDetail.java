package com.qding.api.call.app.qding.v2_4_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.SpeRel;
import com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.SkuGrouponInfo;

import java.util.List;

public class GoodsDetail extends Goods {
	
	private static final long serialVersionUID = 5976974093875045508L;

	
	public GoodsDetail(){
		
	}


	@ExplainAnnotation(explain = "乐购活动简名集合")
	private List<GoodsActivity> goodsActivityList;

	@ExplainAnnotation(explain = "乐购促销标签集合")
	private List<GoodsPromotion> goodsPromotionList;

	@ExplainAnnotation(explain = "商品详情首页评论信息")
	private GoodsCommentBean  wareComments;

	@ExplainAnnotation(explain = "按钮展示状态",desc = "1:可购买，0:不可购买（提示信息不弹框）,-1:不可购买(提示信息弹框),-2:不支持当前系统平台购买")
	private Integer buyBtnStatus = 0;

	@ExplainAnnotation(explain = "购买按钮文字描述",desc = "开售提醒|立即购买")
	private String buyBtnName = "开售提醒";

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

	@ExplainAnnotation(explain = "配送说明",desc = " 上门|自取|周鲜生")
	private String deliveryRemark;

	@ExplainAnnotation(explain = "售罄提醒")
	private String soldOutMsg;

	@ExplainAnnotation(explain = "剩余可购买数",desc="-1：不限购")
	private Integer surplusBuyNum;

	@ExplainAnnotation(explain = "限购总数")
	private Integer restrictionBuyNum;

	@ExplainAnnotation(explain = "条款描述列表")
	private List<ClauseConfig> clauseConfigList;

	@ExplainAnnotation(explain = "是否可以分享",desc = "0：不支持 1：支持")
	private Integer isSupportShare = 0;

	@ExplainAnnotation(explain = "是否支持超出限购原价购买",desc = "0：不支持 1：支持")
	private Integer isSupportSell = 0;

	@ExplainAnnotation(explain = "是否支持七天限购",desc = " 1支持 0不支持")
	private Integer isSdnrtr;
	
	@ExplainAnnotation(explain = "阶梯团购信息 3.3新增")
    private SkuGrouponInfo groupon; 
	

	public Integer getIsSdnrtr() {
		return isSdnrtr;
	}

	public void setIsSdnrtr(Integer isSdnrtr) {
		this.isSdnrtr = isSdnrtr;
	}

	public Integer getIsSupportShare() {
		return isSupportShare;
	}

	public void setIsSupportShare(Integer isSupportShare) {
		this.isSupportShare = isSupportShare;
	}

	public Integer getIsSupportSell() {
		return isSupportSell;
	}

	public void setIsSupportSell(Integer isSupportSell) {
		this.isSupportSell = isSupportSell;
	}

	public List<ClauseConfig> getClauseConfigList() {
		return clauseConfigList;
	}

	public void setClauseConfigList(List<ClauseConfig> clauseConfigList) {
		this.clauseConfigList = clauseConfigList;
	}

	public Integer getSurplusBuyNum() {
		return surplusBuyNum;
	}

	public void setSurplusBuyNum(Integer surplusBuyNum) {
		this.surplusBuyNum = surplusBuyNum;
	}

	public Integer getRestrictionBuyNum() {
		return restrictionBuyNum;
	}

	public void setRestrictionBuyNum(Integer restrictionBuyNum) {
		this.restrictionBuyNum = restrictionBuyNum;
	}

	public String getSoldOutMsg() {
		return soldOutMsg;
	}

	public void setSoldOutMsg(String soldOutMsg) {
		this.soldOutMsg = soldOutMsg;
	}

	public String getDeliveryRemark() {
		return deliveryRemark;
	}

	public void setDeliveryRemark(String deliveryRemark) {
		this.deliveryRemark = deliveryRemark;
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

	public List<GoodsActivity> getGoodsActivityList() {
		return goodsActivityList;
	}

	public void setGoodsActivityList(List<GoodsActivity> goodsActivityList) {
		this.goodsActivityList = goodsActivityList;
	}

	public List<GoodsPromotion> getGoodsPromotionList() {
		return goodsPromotionList;
	}

	public void setGoodsPromotionList(List<GoodsPromotion> goodsPromotionList) {
		this.goodsPromotionList = goodsPromotionList;
	}


	public GoodsCommentBean getWareComments() {
		return wareComments;
	}

	public void setWareComments(GoodsCommentBean wareComments) {
		this.wareComments = wareComments;
	}

	public Integer getBuyBtnStatus() {
		return buyBtnStatus;
	}

	public void setBuyBtnStatus(Integer buyBtnStatus) {
		this.buyBtnStatus = buyBtnStatus;
	}

	public String getBuyBtnName() {
		return buyBtnName;
	}

	public void setBuyBtnName(String buyBtnName) {
		this.buyBtnName = buyBtnName;
	}

	public SkuGrouponInfo getGroupon() {
		return groupon;
	}

	public void setGroupon(SkuGrouponInfo groupon) {
		this.groupon = groupon;
	}

	 
	
	
	
	
	
	
	
	
	
	
	
}
