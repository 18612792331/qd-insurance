package com.qding.api.call.app.qding.v2_0_0.struct.useraccount;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias(value = "userFamilyRelationType")
public class UserFamilyRelationType implements Serializable {

    @ExplainAnnotation(explain = "亲情关系类型",desc = "MOTHER:母亲; FATHER:父亲; WIFE:老婆; HUSBAND:老公; SON:儿子; DAUGHTER:女儿; RELATIVE:亲属; FRIEND:朋友")
    private String type;

    @ExplainAnnotation(explain = "亲情关系描述")
    private String remark;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public UserFamilyRelationType(String type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public UserFamilyRelationType() {
    }
}
