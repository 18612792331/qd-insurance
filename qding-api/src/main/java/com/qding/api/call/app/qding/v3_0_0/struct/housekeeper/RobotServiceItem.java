package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

/**
 * Created by qd on 2017/3/23.
 */
public class RobotServiceItem extends SkipUrl {

    private static final long serialVersionUID = -5740500439570192138L;


    @ExplainAnnotation(explain = "服务项标题")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
