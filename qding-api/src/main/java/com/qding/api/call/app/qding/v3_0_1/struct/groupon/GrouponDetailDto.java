package com.qding.api.call.app.qding.v3_0_1.struct.groupon;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jinhaishan on 17/4/14.
 */
@ExplainAnnotation(explain = "团购详情")
public class GrouponDetailDto implements Serializable {

    private static final long serialVersionUID = 1971153416862700259L;

    @ExplainAnnotation(explain = "团购ID")
    private Long id;


    @ExplainAnnotation(explain = "团购标题")
    private String name;

    @ExplainAnnotation(explain = "团购描述")
    private String description;

    @ExplainAnnotation(explain = "首页图片url")
    private String imgUrl;

    @ExplainAnnotation(explain = "开始时间")
    private Long startTime;

    @ExplainAnnotation(explain = "结束时间")
    private Long endTime;

    @ExplainAnnotation(explain = "业态编号")
    private String productNo;

    @ExplainAnnotation(explain = "商品ID")
    private Long skuId;

    @ExplainAnnotation(explain = "商品名称")
    private String skuName;

    @ExplainAnnotation(explain = "商品类型", desc = "1:实物，2:非实物")
    private Integer skuType;

    @ExplainAnnotation(explain = "原价", desc = "单位（元）")
    private String marketPrice;


     @ExplainAnnotation(explain = "最低成团数量")
    private Integer minimumCount;


    @ExplainAnnotation(explain = "已经购买商品数量")
    private Integer boughtCount;

    @ExplainAnnotation(explain = "每个ID商品数量")
    private Integer limitCount;

    @ExplainAnnotation(explain = "库存商品数量")
    private Integer stockCount;

    @ExplainAnnotation(explain = "相册url", desc = "图片顺序为后台配置的顺序")
    private List<String> imgUrls;

    @ExplainAnnotation(explain = "团购阶梯价格", desc = "阶梯价格的顺序为后台配置的顺序")
    private List<GradientPriceDto> gradientPrices;


    @ExplainAnnotation(explain = "是否分享", desc = "0:否，1:是")
    private Integer isShare;

    @ExplainAnnotation(explain = "分享图片url")
    private String shareImgUrl;

    @ExplainAnnotation(explain = "分享标题")
    private String shareTitle;

    @ExplainAnnotation(explain = "分享内容")
    private String shareContent;


    @ExplainAnnotation(explain = "团购状态", desc = "1:未开团,2:团购进行中,3:已成团,4:团购失败")
    private Integer status;

    @ExplainAnnotation(explain = "图文详情")
    private String content;

    @ExplainAnnotation(explain = "用户已经购买的商品数量")
    private Integer alreadyBuyCount;

    @ExplainAnnotation(explain = "能付参与",desc = "1:可以 0:不可以")
    private Integer isCanJoin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getSkuType() {
        return skuType;
    }

    public void setSkuType(Integer skuType) {
        this.skuType = skuType;
    }

    public Integer getMinimumCount() {
        return minimumCount;
    }

    public void setMinimumCount(Integer minimumCount) {
        this.minimumCount = minimumCount;
    }

    public Integer getBoughtCount() {
        return boughtCount;
    }

    public void setBoughtCount(Integer boughtCount) {
        this.boughtCount = boughtCount;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<GradientPriceDto> getGradientPrices() {
        return gradientPrices;
    }

    public void setGradientPrices(List<GradientPriceDto> gradientPrices) {
        this.gradientPrices = gradientPrices;
    }

    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    public String getShareImgUrl() {
        return shareImgUrl;
    }

    public void setShareImgUrl(String shareImgUrl) {
        this.shareImgUrl = shareImgUrl;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getAlreadyBuyCount() {
        return alreadyBuyCount;
    }

    public void setAlreadyBuyCount(Integer alreadyBuyCount) {
        this.alreadyBuyCount = alreadyBuyCount;
    }

    public Integer getIsCanJoin() {
        return isCanJoin;
    }

    public void setIsCanJoin(Integer isCanJoin) {
        this.isCanJoin = isCanJoin;
    }
}
