package com.qding.api.call.app.qding.v1_3_1.struct.brick.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/9/25.
 */
@Validate
public class ClearAppDataResponseRequest  extends BaseRequest {


    private static final long serialVersionUID = 5243005487501833204L;
    /**
     * 最老版本
     */
    @NotNullValidate
    private String lastDataVersion;

    public String getLastDataVersion() {
        return lastDataVersion;
    }

    public void setLastDataVersion(String lastDataVersion) {
        this.lastDataVersion = lastDataVersion;
    }

    @Override
    public String toString() {
        return "ClearAppDataResponseRequest [lastDataVersion=" + lastDataVersion
                + ", toString()=" + super.toString() + "]";
    }
}
