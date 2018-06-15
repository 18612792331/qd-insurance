package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/27.
 */
@Validate
public class GroupNoticeDelRequest extends BaseRequest {

    private static final long serialVersionUID = -8323047873393750114L;

    /**
     * 公告Id
     */
    @NotNullValidate
    private String billboardId;

    /**
     * 操作者名称
     */
    @NotNullValidate
    private String optAt;

    //操作者ID
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOptAt() {
        return optAt;
    }

    public void setOptAt(String optAt) {
        this.optAt = optAt;
    }

    public String getBillboardId() {
        return billboardId;
    }

    public void setBillboardId(String billboardId) {
        this.billboardId = billboardId;
    }

    @Override
    public String toString() {
        return "GroupNoticeDelRequest [userId="+userId+",optAt="+optAt+",billboardId=" + billboardId  +
                " ,toString ="+ super.toString() +"]";
    }
}
