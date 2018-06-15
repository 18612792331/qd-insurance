package com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/6/20.
 */
@Validate
public class SetSellRemindRequest extends BaseRequest {

    private static final long serialVersionUID = -489386688219590668L;

    @ExplainAnnotation (explain = "会员ID")
    @NotNullValidate
    private String memberId;

    @ExplainAnnotation (explain = "货品ID")
    @NotNullValidate
    private String skuId;

    @ExplainAnnotation (explain = "社区ID")
    @NotNullValidate
    private String projectId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "SetSellRemindRequest{" +
                "memberId='" + memberId + '\'' +
                ", skuId='" + skuId + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
