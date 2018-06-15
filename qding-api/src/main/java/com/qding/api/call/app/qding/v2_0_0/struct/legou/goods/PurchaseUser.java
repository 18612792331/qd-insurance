package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.UserInfo;

import java.io.Serializable;

/**
 * Created by qd on 2015/12/22.
 */
public class PurchaseUser extends UserInfo implements Serializable {

    private static final long serialVersionUID = 3710807234461817395L;

    @ExplainAnnotation(explain = "购买人社区名称")
    private String communityName;

    @ExplainAnnotation(explain = "购买人社区ID")
    private String projectId;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
