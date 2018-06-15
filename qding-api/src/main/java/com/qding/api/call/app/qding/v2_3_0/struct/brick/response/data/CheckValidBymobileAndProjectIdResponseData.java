package com.qding.api.call.app.qding.v2_3_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/8/16.
 */
public class CheckValidBymobileAndProjectIdResponseData  extends ResponseData {

    private static final long serialVersionUID = 5855160491823680486L;

    @ExplainAnnotation (explain = "是否用户有权限",desc = "1:有权访问，0：无权访问")
    private Integer havePower;

    public Integer getHavePower() {
        return havePower;
    }

    public void setHavePower(Integer havePower) {
        this.havePower = havePower;
    }

    @Override
    public String toString() {
        return "CheckValidBymobileAndProjectIdResponseData{" +
                "havePower=" + havePower +
                '}';
    }
}
