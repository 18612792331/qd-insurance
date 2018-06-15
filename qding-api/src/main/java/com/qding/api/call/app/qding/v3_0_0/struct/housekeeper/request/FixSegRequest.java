package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2017/3/27.
 */
public class FixSegRequest extends BaseRequest {

    private static final long serialVersionUID = 7878957657700557756L;

    @ExplainAnnotation (explain = "原词")
    private String seg;

    public String getSeg() {
        return seg;
    }

    public void setSeg(String seg) {
        this.seg = seg;
    }

    @Override
    public String toString() {
        return "FixSegRequest{" +
                "seg='" + seg + '\'' +
                '}';
    }
}
