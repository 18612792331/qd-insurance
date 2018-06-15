package com.qding.api.call.app.qding.v2_4_1.struct.task;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;

/**
 * Created by qd on 2016/7/26.
 */
public class TaskBtnSkip extends BtnSkip {

    @ExplainAnnotation(explain = "按钮名称色值")
    private String btnNameColor;

    @ExplainAnnotation(explain = "按钮色值")
    private String btnBgColor;

    public String getBtnNameColor() {
        return btnNameColor;
    }

    public void setBtnNameColor(String btnNameColor) {
        this.btnNameColor = btnNameColor;
    }

    public String getBtnBgColor() {
        return btnBgColor;
    }

    public void setBtnBgColor(String btnBgColor) {
        this.btnBgColor = btnBgColor;
    }
}
