package com.qding.api.call.app.qding.v2_0_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2016/1/11.
 */
public class BtnSkip extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -7996305882258249834L;

    @ExplainAnnotation(explain = "按钮显示名称")
    private String btnName;

    @ExplainAnnotation(explain = "按钮类型",desc = "0:其他按钮，1:支付按钮,2:签收，3：客服")
    private Integer btnType = 0;

    @ExplainAnnotation(explain = "颜色值")
    private String btnColor = "#333333";

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public Integer getBtnType() {
        return btnType;
    }

    public void setBtnType(Integer btnType) {
        this.btnType = btnType;
    }

    public String getBtnColor() {
        return btnColor;
    }

    public void setBtnColor(String btnColor) {
        this.btnColor = btnColor;
    }
}
