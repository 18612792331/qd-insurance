package com.qding.api.call.app.qding.v2_8_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.BoardImg;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/12/16.
 */
public class GetThemeContainMultiProjectResponse extends ResponseData {

    private static final long serialVersionUID = 8313374755518273163L;

    @ExplainAnnotation(explain = "是否含有多个社区")
    private Boolean multiProject;

    public Boolean getMultiProject() {
        return multiProject;
    }

    public void setMultiProject(Boolean multiProject) {
        this.multiProject = multiProject;
    }

    @Override
    public String toString() {
        return "GetThemeContainMultiProjectResponse{" +
                "multiProject=" + multiProject +
                '}';
    }
}
