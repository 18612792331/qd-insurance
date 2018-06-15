package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 活动场次信息
 * Created by jinhaishan on 17/5/12.
 */
@Setter
@Getter
public class ActivitySessionDto implements Serializable{

    private static final long serialVersionUID = -833276508229619429L;

    @ExplainAnnotation(explain = "场次ID")
    private String id;

    @ExplainAnnotation(explain = "场次名称")
    private String title;

    @ExplainAnnotation(explain = "限报人数")
    private Integer limitCount;

    @ExplainAnnotation(explain = "已报人数")
    private int enrollCount;

    public ActivitySessionDto(String id, String title, Integer limitCount, Integer enrollCount) {
        this.id = id;
        this.title = title;
        this.limitCount = limitCount;
        this.enrollCount = enrollCount;
    }

    public ActivitySessionDto() {
    }
}
