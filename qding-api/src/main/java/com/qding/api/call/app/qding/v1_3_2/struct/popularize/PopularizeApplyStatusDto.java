package com.qding.api.call.app.qding.v1_3_2.struct.popularize;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/6.
 */
public class PopularizeApplyStatusDto implements Serializable {

    private static final long serialVersionUID = -21584291728609842L;

    /**
     * 申请状态码
     */
    private Integer applyStatus;

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public PopularizeApplyStatusDto(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }
}
