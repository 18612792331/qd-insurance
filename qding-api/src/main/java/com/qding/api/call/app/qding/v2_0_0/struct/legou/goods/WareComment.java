package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.UserInfo;

/**
 * Created by qd on 2016/1/5.
 */
public class WareComment extends UserInfo {

    @ExplainAnnotation(explain = "发布时间")
    private  Long publishTime;

    @ExplainAnnotation(explain = "评价内容")
    private String comment;

    @ExplainAnnotation(explain = "是否匿名",desc = "1:不匿名 0：匿名")
    private Integer isAnonymity ;

    @ExplainAnnotation(explain = "社区描述")
    private String projectDesc;

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Integer getIsAnonymity() {
        return isAnonymity;
    }

    public void setIsAnonymity(Integer isAnonymity) {
        this.isAnonymity = isAnonymity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public Long getPublishTime() {
        return publishTime;
    }
}
