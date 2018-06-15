package com.qding.api.call.app.qding.v2_0_0.struct.useraccount;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.solr.common.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

@XStreamAlias(value = "userFamilyRelationInfo")
public class UserFamilyRelationInfo implements Serializable {
    //private String id;
    @ExplainAnnotation(explain = "开通者ID")
    private String midFrom;

    @ExplainAnnotation(explain = "使用者ID")
    private String midTo;

    @ExplainAnnotation(explain = "类型")
    private String type;

    //private Integer quota;

    //private String status;

    @ExplainAnnotation(explain = "创建时间")
    private Long createTime;

    //private Long updateTime;
    @ExplainAnnotation(explain = "开通者名称")
    private String nameFrom;

    @ExplainAnnotation(explain = "开通者手机号")
    private String mobileFrom;

    @ExplainAnnotation(explain = "使用者名称")
    private String nameTo;

    @ExplainAnnotation(explain = "使用者手机号")
    private String mobileTo;

    @ExplainAnnotation(explain = "开通者头像")
    private String headImgFrom;

    @ExplainAnnotation(explain = "使用者头像")
    private String headImgTo;

    @ExplainAnnotation(explain = "亲情关系类型")
    private String fromToType;

    @ExplainAnnotation(explain = "支付信息")
    private PaymentInfo paymentInfo;

    @ExplainAnnotation(explain = "千丁券信息")
    private TicketInfo ticketInfo;

    private static final long serialVersionUID = 1L;

    public String getMidFrom() {
        return midFrom;
    }

    public void setMidFrom(String midFrom) {
        this.midFrom = midFrom == null ? null : midFrom.trim();
    }

    public String getMidTo() {
        return midTo;
    }

    public void setMidTo(String midTo) {
        this.midTo = midTo == null ? null : midTo.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        if (StringUtils.isEmpty(nameFrom)) {
            this.nameFrom = "千丁用户";
        }
        this.nameFrom = nameFrom;
    }

    public String getMobileFrom() {
        return mobileFrom;
    }

    public void setMobileFrom(String mobileFrom) {
        if (mobileFrom != null && mobileFrom.length() == 11) {
            this.mobileFrom = mobileFrom.substring(0, 3) + "****" + mobileFrom.substring(7);
        } else {
            this.mobileFrom = mobileFrom;
        }
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        if (StringUtils.isEmpty(nameTo)) {
            this.nameTo = "千丁用户";
        }
        this.nameTo = nameTo;
    }

    public String getMobileTo() {
        return mobileTo;
    }

    public void setMobileTo(String mobileTo) {
        if (mobileTo != null && mobileTo.length() == 11) {
            this.mobileTo = mobileTo.substring(0, 3) + "****" + mobileTo.substring(7);
        } else {
            this.mobileTo = mobileTo;
        }
    }

    public String getFromToType() {
        return fromToType;
    }

    public void setFromToType(String fromToType) {
        this.fromToType = fromToType;
    }

    public String getHeadImgFrom() {
        return headImgFrom;
    }

    public void setHeadImgFrom(String headImgFrom) {
        this.headImgFrom = headImgFrom;
    }

    public String getHeadImgTo() {
        return headImgTo;
    }

    public void setHeadImgTo(String headImgTo) {
        this.headImgTo = headImgTo;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public TicketInfo getTicketInfo() {
        return ticketInfo;
    }

    public void setTicketInfo(TicketInfo ticketInfo) {
        this.ticketInfo = ticketInfo;
    }
}