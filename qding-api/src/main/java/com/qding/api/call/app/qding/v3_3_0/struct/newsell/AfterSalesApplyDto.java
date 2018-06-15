package com.qding.api.call.app.qding.v3_3_0.struct.newsell;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class AfterSalesApplyDto implements Serializable {

    private static final long serialVersionUID = -7245499738458343963L;


    /**
     * 申请人会员ID
     */
    @Getter
    @Setter
    private String memberId;

    /**
     * 申请售后的千丁订单
     */
    @Getter
    @Setter
    private String qdOrderCode;

    /**
     申请售后的第三方订单
     */
    @Getter
    @Setter
    private String thirdOrderCode;

    /**
     * 申请原因
     */
    @Getter
    @Setter
    private String reason;

    /**
     * 申请售后备注
     */
    @Getter
    @Setter
    private String remark;



    /**
     * 第三方渠道类型 1：点滴
     */
    @Getter
    @Setter
    private Integer sourceType;


    /**
     * 第三方售后申请结构体 (json格式针对不同的渠道商，结构不一致，所以在接入新渠道的时候需要沟通)
     */
    @Getter
    @Setter
    private String structure;


}
