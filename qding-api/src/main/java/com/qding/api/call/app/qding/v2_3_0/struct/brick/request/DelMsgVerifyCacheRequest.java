package com.qding.api.call.app.qding.v2_3_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2016/6/20.
 */
public class DelMsgVerifyCacheRequest  extends BaseRequest {

    private static final long serialVersionUID = -2654945083642684648L;

    @ExplainAnnotation (explain = "手机号")
    private String mobile;

    @ExplainAnnotation (explain = "类型",desc = "1:注册，2：忘记密码，3：绑定手机号，4：绑定房屋，5：更新手机号")
    private String type;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DelMsgVerifyCacheRequest{" +
                "mobile='" + mobile + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
