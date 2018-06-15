package com.qding.api.call.app.qding.v2_5_0.struct.brick;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/8/22.
 */
public class Province implements Serializable {

    private static final long serialVersionUID = 4621777737052394283L;

    @ExplainAnnotation (explain = "省份ID")
    private String id;

    @ExplainAnnotation (explain = "省份名称")
    private String provinceName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
