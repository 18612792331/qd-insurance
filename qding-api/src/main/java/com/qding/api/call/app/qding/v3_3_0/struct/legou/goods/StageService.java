package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class StageService extends SkipUrl implements Serializable{

    private static final long serialVersionUID = -2093661421768235821L;


    @ExplainAnnotation(explain = "驿站服务名称")
    @Getter
    @Setter
    private String serviceName;
}
