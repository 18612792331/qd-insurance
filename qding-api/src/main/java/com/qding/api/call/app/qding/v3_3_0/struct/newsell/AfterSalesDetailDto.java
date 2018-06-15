package com.qding.api.call.app.qding.v3_3_0.struct.newsell;

import com.qding.api.annotation.ExplainAnnotation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class AfterSalesDetailDto extends AfterSalesSkuDetailDto  implements Serializable {

    private static final long serialVersionUID = -2832128143721242658L;

    @Getter
    @Setter
    @ExplainAnnotation(explain = "千丁售后申请ID")
    private String applyId;



    @Getter
    @Setter
    @ExplainAnnotation(explain = "千丁订单")
    private String qdOrderCode;



    @Getter
    @Setter
    @ExplainAnnotation(explain = "申请时间")
    private Date createTime ;



    @Getter
    @Setter
    @ExplainAnnotation(explain = "申请原因")
    private String reason;



    @Getter
    @Setter
    @ExplainAnnotation(explain = "申请类型 1:退货 2:换货 3:维修")
    private Integer applyType;


    @Getter
    @Setter
    @ExplainAnnotation(explain = "最新状态")
    private Integer lastStatus;

    @Getter
    @Setter
    @ExplainAnnotation(explain = "最新状态描述")
    private String lastStatusDesc;


    @Getter
    @Setter
    @ExplainAnnotation(explain = "千丁映射物流公司ID")
    private Long qdCompanyId;


    @Getter
    @Setter
    @ExplainAnnotation(explain = "千丁物流公司名称")
    private String qdCompany;

    @Getter
    @Setter
    @ExplainAnnotation(explain = "物流订单编号")
    private String logisticsCode;


    @Getter
    @Setter
    @ExplainAnnotation(explain = "第三方售后申请结构体")
    private String structure;


    @Getter
    @Setter
    @ExplainAnnotation(explain = "渠道类型 1：点滴")
    private Integer sourceType;




}
