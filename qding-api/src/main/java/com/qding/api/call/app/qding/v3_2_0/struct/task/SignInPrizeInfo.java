package com.qding.api.call.app.qding.v3_2_0.struct.task;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by jinhaishan on 17/7/24.
 */
public class SignInPrizeInfo implements Serializable{
    private static final long serialVersionUID = 3851381996895187330L;

    @ExplainAnnotation(explain = "商品ID")
    private Long goodsId;

    @ExplainAnnotation(explain = "提示图")
    private String goodsImgUrl;

    @ExplainAnnotation(explain = "提示图")
    private String tipImgUrl;

    @ExplainAnnotation(explain = "最小签到次数（领取条件）")
    private Integer minSignInCount;

    @ExplainAnnotation(explain = "领取说明")
    private String description;

    @ExplainAnnotation(explain = "商品名称")
    private String goodsName;

    @ExplainAnnotation(explain = "领取状态", desc = "0：不可领取，1：可领取，2：已领取")
    private String status;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImgUrl() {
        return goodsImgUrl;
    }

    public void setGoodsImgUrl(String goodsImgUrl) {
        this.goodsImgUrl = goodsImgUrl;
    }

    public String getTipImgUrl() {
        return tipImgUrl;
    }

    public void setTipImgUrl(String tipImgUrl) {
        this.tipImgUrl = tipImgUrl;
    }

    public Integer getMinSignInCount() {
        return minSignInCount;
    }

    public void setMinSignInCount(Integer minSignInCount) {
        this.minSignInCount = minSignInCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
