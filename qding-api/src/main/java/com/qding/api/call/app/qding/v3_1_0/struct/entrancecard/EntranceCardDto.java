package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;

@ExplainAnnotation(explain = "门禁卡信息")
public class EntranceCardDto implements Serializable {

    private static final long serialVersionUID = -7770487005752101081L;

    @ExplainAnnotation(explain = "门禁卡ID", desc = "")
    private String id;

    @ExplainAnnotation(explain = "门禁卡号", desc = "")
    private String cardNo;

    @ExplainAnnotation(explain = "门禁卡SN", desc = "开卡操作时由门禁服务方生成的串码")
    private String sn;

    @ExplainAnnotation(explain = "门禁卡状态", desc = "0-待刷卡，1-正常，2-已销卡，3-已冻结，4-已过期,5-未绑的新卡")
    private Integer status;

    @ExplainAnnotation(explain = "有效期开始日期", desc = "")
    private String effectiveBegin;

    @ExplainAnnotation(explain = "有效期结束日期", desc = "")
    private String effectiveEnd;

    @ExplainAnnotation(explain = "有效期是否即将到期", desc = "0-否,1-是")
    private Integer isSoonExpire;

    @ExplainAnnotation(explain = "续期后有效期开始日期", desc = "卡即将到期时返回")
    private String renewalBegin;

    @ExplainAnnotation(explain = "续期后有效期结束日期", desc = "卡即将到期时返回")
    private String renewalEnd;

    public String getRenewalBegin() {
        return renewalBegin;
    }

    public void setRenewalBegin(String renewalBegin) {
        this.renewalBegin = renewalBegin;
    }

    public String getRenewalEnd() {
        return renewalEnd;
    }

    public void setRenewalEnd(String renewalEnd) {
        this.renewalEnd = renewalEnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEffectiveBegin() {
        return effectiveBegin;
    }

    public void setEffectiveBegin(String effectiveBegin) {
        this.effectiveBegin = effectiveBegin;
    }

    public String getEffectiveEnd() {
        return effectiveEnd;
    }

    public void setEffectiveEnd(String effectiveEnd) {
        this.effectiveEnd = effectiveEnd;
    }

    public Integer getIsSoonExpire() {
        return isSoonExpire;
    }

    public void setIsSoonExpire(Integer isSoonExpire) {
        this.isSoonExpire = isSoonExpire;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

}
