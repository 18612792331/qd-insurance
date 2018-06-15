package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/13.
 */
public class ThemeRuleForMember implements Serializable {


    @ExplainAnnotation (explain = "话题类型",desc = "1:普通,2:投票,3:活动")
    private Integer type;

    @ExplainAnnotation (explain = "该类型可否使用状态",desc = "1:可用,0:不可用")
    private Integer status;

    @ExplainAnnotation (explain = "是否达到上限",desc = "1:达到上限,0:未达到上限")
    private Integer isLimit;

    @ExplainAnnotation(explain = "上限后的提示话术")
    private String remindMsg;

    public ThemeRuleForMember (){

    }

    public ThemeRuleForMember(Integer type, Integer status, Integer isLimit, String remindMsg) {
        this.type = type;
        this.status = status;
        this.isLimit = isLimit;
        this.remindMsg = remindMsg;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(Integer isLimit) {
        this.isLimit = isLimit;
    }

    public String getRemindMsg() {
        return remindMsg;
    }

    public void setRemindMsg(String remindMsg) {
        this.remindMsg = remindMsg;
    }

    @Override
    public String toString() {
        return "ThemeRuleForMember{" +
                "type=" + type +
                ", status=" + status +
                ", isLimit=" + isLimit +
                ", remindMsg='" + remindMsg + '\'' +
                '}';
    }
}
