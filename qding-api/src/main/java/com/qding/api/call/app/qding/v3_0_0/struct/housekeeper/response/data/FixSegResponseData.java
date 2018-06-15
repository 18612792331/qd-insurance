package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/27.
 */
public class FixSegResponseData extends ResponseData {

    private static final long serialVersionUID = 5630554505037934227L;

    @ExplainAnnotation (explain = "修正后的词")
    private String seg;

    public String getSeg() {
        return seg;
    }

    public void setSeg(String seg) {
        this.seg = seg;
    }

    @Override
    public String toString() {
        return "FixSegResponseData{" +
                "seg='" + seg + '\'' +
                '}';
    }
}
