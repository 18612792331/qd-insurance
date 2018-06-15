package com.qding.api.call.app.qding.v2_4_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

/**
 * Created by qd on 2016/6/16.
 */
public class GoodsActivity extends SkipUrl {

    @ExplainAnnotation (explain = "活动简名",desc = "eg：该商品正参与618千丁日")
    private String activityDesc;

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }
}
