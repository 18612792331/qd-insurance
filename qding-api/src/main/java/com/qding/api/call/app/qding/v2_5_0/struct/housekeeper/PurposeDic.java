package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/11/18.
 */
public class PurposeDic implements Serializable {

    private static final long serialVersionUID = 5296227329824183063L;

    @ExplainAnnotation (explain = "来访目的类型")
    private  Integer purposeType;

    @ExplainAnnotation (explain = "来访目的名称")
    private String purposeName;

    @ExplainAnnotation (explain = "限制次数",desc = "小于等于0:无限次，大于0:是几就是几次")
    private Integer validTimes;

    public Integer getPurposeType() {
        return purposeType;
    }

    public void setPurposeType(Integer purposeType) {
        this.purposeType = purposeType;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }

    public Integer getValidTimes() {
        return validTimes;
    }

    public void setValidTimes(Integer validTimes) {
        this.validTimes = validTimes;
    }
}
