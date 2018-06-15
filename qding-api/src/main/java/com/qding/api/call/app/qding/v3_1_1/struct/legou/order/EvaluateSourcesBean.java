package com.qding.api.call.app.qding.v3_1_1.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/6/22.
 */
public class EvaluateSourcesBean implements Serializable {

    private static final long serialVersionUID = 2670532323531482091L;

    @ExplainAnnotation (explain = "商品ID")
    private String skuId;

    @ExplainAnnotation (explain = "商品名称")
    private String goodsName;

    @ExplainAnnotation (explain = "评价主体图片")
    private String goodsImg;

    @ExplainAnnotation (explain = "商品简介")
    private String goodsDesc;

    @ExplainAnnotation (explain = "用户上传的图片")
    private List<String> evaluateImgs;

    @ExplainAnnotation (explain = "评分")
    private int score;

    @ExplainAnnotation (explain = "标签列表")
    private List<String> flagNames;

    @ExplainAnnotation (explain = "评价会员id")
    private String memberId;

    @ExplainAnnotation (explain = "评价人名称")
    private String memberName;

    @ExplainAnnotation (explain = "是否匿名评价" ,desc = "0:隐藏 1:显示")
    private int anonymousFlag;

    @ExplainAnnotation (explain = "评价时间" )
    private Long createAt;

    @ExplainAnnotation(explain = "评价内容")
    private String evaluateContent;

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public List<String> getEvaluateImgs() {
        return evaluateImgs;
    }

    public void setEvaluateImgs(List<String> evaluateImgs) {
        this.evaluateImgs = evaluateImgs;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getFlagNames() {
        return flagNames;
    }

    public void setFlagNames(List<String> flagNames) {
        this.flagNames = flagNames;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getAnonymousFlag() {
        return anonymousFlag;
    }

    public void setAnonymousFlag(int anonymousFlag) {
        this.anonymousFlag = anonymousFlag;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    @Override
    public String toString() {
        return "EvaluateSourcesBean{" +
                "skuId='" + skuId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsDesc='" + goodsDesc + '\'' +
                ", evaluateImgs=" + evaluateImgs +
                ", score=" + score +
                ", flagNames=" + flagNames +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", anonymousFlag=" + anonymousFlag +
                ", createAt=" + createAt +
                ", evaluateContent='" + evaluateContent + '\'' +
                '}';
    }
}
