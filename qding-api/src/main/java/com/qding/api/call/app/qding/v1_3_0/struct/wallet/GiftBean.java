package com.qding.api.call.app.qding.v1_3_0.struct.wallet;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

/**
 * Created by qd on 2017/7/21.
 */
public class GiftBean extends SkipUrl {

    @ExplainAnnotation(explain = "是否显示礼包",desc = "0:不显示,1:显示")
    private Integer showFlag = 0;

    public Integer getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }
}
