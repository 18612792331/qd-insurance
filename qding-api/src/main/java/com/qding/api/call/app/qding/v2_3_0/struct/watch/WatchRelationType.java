package com.qding.api.call.app.qding.v2_3_0.struct.watch;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias(value = "watchRelationType")
public class WatchRelationType implements Serializable {

    @ExplainAnnotation(explain = "手表关系类型", desc = "1:妈妈; 2:爸爸; 3:爷爷; 4:奶奶; 5:外公; 6:外婆; 7:其他亲属;")
    private String type;

    @ExplainAnnotation(explain = "手表关系描述")
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

    public WatchRelationType(String type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public WatchRelationType() {
    }
}
