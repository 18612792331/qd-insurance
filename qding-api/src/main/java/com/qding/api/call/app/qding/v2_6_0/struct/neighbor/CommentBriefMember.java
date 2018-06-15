package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

/**
 * Created by qd on 2016/9/12.
 */
public class CommentBriefMember extends BriefMember {


    @ExplainAnnotation(explain = "是否是楼主")
    private Integer isLz;

    public Integer getIsLz() {
        return isLz;
    }

    public void setIsLz(Integer isLz) {
        this.isLz = isLz;
    }
}
