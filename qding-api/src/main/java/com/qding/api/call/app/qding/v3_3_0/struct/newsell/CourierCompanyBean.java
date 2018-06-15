package com.qding.api.call.app.qding.v3_3_0.struct.newsell;

import com.qding.api.annotation.ExplainAnnotation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class CourierCompanyBean implements Serializable {

    private static final long serialVersionUID = 8656192620384208896L;

    @Setter
    @Getter
    @ExplainAnnotation(explain = "物流公司ID")
    private Long id;

    @Setter
    @Getter
    @ExplainAnnotation(explain = "物流公司名称")
    private String companyName;

    @Setter
    @Getter
    @ExplainAnnotation(explain = "物流公司编号")
    private String companyCode;

    @Setter
    @Getter
    @ExplainAnnotation(explain = "物流公司备注")
    private String remark;
}
