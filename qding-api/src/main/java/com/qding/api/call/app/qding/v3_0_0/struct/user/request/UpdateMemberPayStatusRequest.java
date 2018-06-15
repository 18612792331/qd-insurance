package com.qding.api.call.app.qding.v3_0_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/5/10.
 */
@Validate
public class UpdateMemberPayStatusRequest extends BaseRequest {

    private static final long serialVersionUID = 6330270835571682937L;

    @ExplainAnnotation (explain = "物业费代扣状态",desc = "1:开通，0：关闭")
    @NotNullValidate
    private Integer payStatus;

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public String toString() {
        return "UpdateMemberPayStatusRequest{" +
                "payStatus=" + payStatus +
                '}';
    }
}
