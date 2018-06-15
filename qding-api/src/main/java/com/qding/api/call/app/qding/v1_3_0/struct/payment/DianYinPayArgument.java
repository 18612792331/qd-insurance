package com.qding.api.call.app.qding.v1_3_0.struct.payment;

import com.qding.api.annotation.ExplainAnnotation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class DianYinPayArgument implements Serializable {

    private static final long serialVersionUID = 2743316052811101125L;

    @ExplainAnnotation(explain = "用户号，识别用户身份")
    @Setter
    @Getter
    private String userNo;

    @ExplainAnnotation(explain = "支付类型，0-余额 1-快捷")
    @Setter
    @Getter
    private Integer payType;

    @ExplainAnnotation(explain = "商户号")
    @Setter
    @Getter
    private String  merchantId;

    @ExplainAnnotation(explain = "订单号，同商户签名srcData中的orderId")
    @Setter
    @Getter
    private String orderId;

    @ExplainAnnotation(explain = "下单时间，取当前时间即可")
    @Setter
    @Getter
    private String orderTime;

    @ExplainAnnotation(explain = "金额，单位：分")
    @Setter
    @Getter
    private String totalAmt;

    @ExplainAnnotation(explain = "商户下单接口返回")
    @Setter
    @Getter
    private String token;
}
