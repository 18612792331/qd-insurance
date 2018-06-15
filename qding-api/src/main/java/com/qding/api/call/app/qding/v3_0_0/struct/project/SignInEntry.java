package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by jinhaishan on 17/7/18.
 */
public class SignInEntry  extends SkipUrl implements Serializable {

    @ExplainAnnotation(explain = "是否显示签到", desc = "true：显示，false:不显示")
    private boolean show;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
