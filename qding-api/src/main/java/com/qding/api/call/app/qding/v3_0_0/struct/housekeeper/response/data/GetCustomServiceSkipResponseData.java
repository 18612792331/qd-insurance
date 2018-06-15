package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/5/16.
 */
public class GetCustomServiceSkipResponseData extends ResponseData {

    private static final long serialVersionUID = -6018781246781065164L;

    @ExplainAnnotation (explain = "客服跳转")
    private String skipModel = "";

    @ExplainAnnotation (explain = "提示信息")
    private String msg;

    public String getSkipModel() {
        return skipModel;
    }

    public void setSkipModel(String skipModel) {
        this.skipModel = skipModel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "GetCustomServiceSkipResponseData{" +
                "skipModel='" + skipModel + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
