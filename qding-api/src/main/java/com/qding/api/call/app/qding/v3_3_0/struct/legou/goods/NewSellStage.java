package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

public class NewSellStage implements Serializable {


    @ExplainAnnotation(explain = "驿站工具栏")
    @Getter
    @Setter
    private List<StageService> stages;

    @ExplainAnnotation(explain = "驿站消息")
    @Getter
    @Setter
    private String stageNotice;

}
