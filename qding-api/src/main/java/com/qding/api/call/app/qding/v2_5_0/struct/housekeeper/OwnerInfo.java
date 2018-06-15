package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias(value = "ownerInfo")
public class OwnerInfo implements Serializable {

    private static final long serialVersionUID = 6635484720501561099L;

    @ExplainAnnotation(explain = "姓名")
    private String name;

    @ExplainAnnotation(explain = "手机号")
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
