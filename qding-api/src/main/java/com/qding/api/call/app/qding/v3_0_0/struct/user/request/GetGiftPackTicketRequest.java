package com.qding.api.call.app.qding.v3_0_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/3/3.
 */
@Validate
public class GetGiftPackTicketRequest extends BaseRequest {

    private static final long serialVersionUID = 1021059837406513213L;

    @ExplainAnnotation(explain = "礼包ID")
    @NotNullValidate
    private String giftId;

    @ExplainAnnotation(explain = "领取类型", desc = "1. 实物  | 2. 新手礼包 |  3. 回馈礼包")
    @NotNullValidate
    private Integer receiveType;

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public GetGiftPackTicketRequest() {
    }

    public GetGiftPackTicketRequest(String giftId, Integer receiveType) {
        this.giftId = giftId;
        this.receiveType = receiveType;
    }
}
