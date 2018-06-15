package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.EntranceCardDto;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.ProjectDto;
import com.qding.api.struct.ResponseData;

public class SettingResponseData extends ResponseData {

    private static final long serialVersionUID = 330762860313230709L;

    @ExplainAnnotation(explain = "门禁卡信息", desc = "")
    private EntranceCardDto card;

    @ExplainAnnotation(explain = "用户绑定房屋数量", desc = "房屋数量大于列表数量时，显示“重新选择”按钮")
    private Integer memberRoomNum;

    @ExplainAnnotation(explain = "用户房屋列表最大数量", desc = "后端配置，避免前段写死")
    private Integer roomListMaxNum;

    @ExplainAnnotation(explain = "用户具有业主身份的社区列表", desc = "")
    private List<ProjectDto> projectList;

    public EntranceCardDto getCard() {
        return card;
    }

    public void setCard(EntranceCardDto card) {
        this.card = card;
    }

    public List<ProjectDto> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectDto> projectList) {
        this.projectList = projectList;
    }

    public Integer getMemberRoomNum() {
        return memberRoomNum;
    }

    public void setMemberRoomNum(Integer memberRoomNum) {
        this.memberRoomNum = memberRoomNum;
    }

    public Integer getRoomListMaxNum() {
        return roomListMaxNum;
    }

    public void setRoomListMaxNum(Integer roomListMaxNum) {
        this.roomListMaxNum = roomListMaxNum;
    }

}
