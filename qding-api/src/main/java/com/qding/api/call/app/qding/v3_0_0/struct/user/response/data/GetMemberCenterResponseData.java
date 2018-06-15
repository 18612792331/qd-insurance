package com.qding.api.call.app.qding.v3_0_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.user.GiftLableDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.user.MemberSpecialActivityDTO;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/7/12.
 */
public class GetMemberCenterResponseData extends ResponseData {

    private static final long serialVersionUID = -4609097642459866430L;

    @ExplainAnnotation(explain = "我的礼包")
    private GiftLableDTO entity;

    @ExplainAnnotation(explain = "任务列表",desc = "目前只针对活动任务项")
    private List<MemberSpecialActivityDTO> taskList;

    public GiftLableDTO getEntity() {
        return entity;
    }

    public void setEntity(GiftLableDTO entity) {
        this.entity = entity;
    }

    public List<MemberSpecialActivityDTO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<MemberSpecialActivityDTO> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "GetMemberCenterResponseData{" +
                "entity=" + entity +
                ", taskList=" + taskList +
                '}';
    }
}
