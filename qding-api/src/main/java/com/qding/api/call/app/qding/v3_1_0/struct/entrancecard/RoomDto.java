package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard;
import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
@ExplainAnnotation(explain = "房屋绑定信息")
public class RoomDto implements Serializable {

    private static final long serialVersionUID = -7770487005752101081L;
    
    @NotNullValidate
    @ExplainAnnotation(explain = "房屋ID", desc = "")
    private String roomId;

    @ExplainAnnotation(explain = "房屋名称", desc = "")
    private String roomName;

    @NotNullValidate
    @ExplainAnnotation(explain = "社区ID", desc = "")
    private String projectId;

    @ExplainAnnotation(explain = "社区名称", desc = "")
    private String projectName;

    @ExplainAnnotation(explain = "城市ID", desc = "")
    private String cityId;

    @ExplainAnnotation(explain = "城市名称", desc = "")
    private String cityName;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
