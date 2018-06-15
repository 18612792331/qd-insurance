package com.qding.api.call.app.qding.v3_0_0.struct.user;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/3.
 */
public class GiftInfoDTO extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -6721949825911097766L;

    @ExplainAnnotation(explain = "礼包id")
    private String giftId;
    
    @ExplainAnnotation(explain = "礼包名称")
    private String giftName;
    
    @ExplainAnnotation(explain = "礼包类型" ,desc = "1:礼包商品 | 2:礼包券")
    private String giftType;

    @ExplainAnnotation(explain = "礼包图片")
    private String giftImg;

    @ExplainAnnotation(explain = "礼包简介")
    private String desc;
    
    @ExplainAnnotation(explain = "商品ID")
    private String skuId;

    /**
     * 前端需要根据1-4判断按钮状态，0，无业务意义，仅为兼容已有版本而存在
     */
    @ExplainAnnotation(explain = "领取状态", desc = "0-不可领取，1-可领取，2-当前社区已领取，3-未绑定房屋，4-资料不完善，5-其他社区已领取")
    private Integer canUse;

    @ExplainAnnotation(explain = "领取类型", desc = "1. 实物  | 2. 新手礼包 |  3. 感恩礼包")
    private Integer receiveType;

    public GiftInfoDTO(String giftId, String giftName, String giftType, String giftImg, String desc, String skuId,
            Integer canUse,Integer receiveType) {
        super();
        this.giftId = giftId;
        this.giftName = giftName;
        this.giftType = giftType;
        this.giftImg = giftImg;
        this.desc = desc;
        this.skuId = skuId;
        this.canUse = canUse;
        this.receiveType =receiveType;
    }

    
    public String getGiftName() {
        return giftName;
    }

    
    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public GiftInfoDTO() {
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public Integer getCanUse() {
        return canUse;
    }

    public void setCanUse(Integer canUse) {
        this.canUse = canUse;
    }

    public String getGiftImg() {
        return giftImg;
    }

    public void setGiftImg(String giftImg) {
        this.giftImg = giftImg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }
}
