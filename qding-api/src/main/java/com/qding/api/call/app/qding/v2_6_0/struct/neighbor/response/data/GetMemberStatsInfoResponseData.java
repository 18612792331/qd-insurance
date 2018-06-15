package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.UserInfo;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/9/9.
 */
public class GetMemberStatsInfoResponseData extends ResponseData {

    private static final long serialVersionUID = -4659715064458378611L;

    private UserInfo entity;

    private String lastCommunity;

    public UserInfo getEntity() {
        return entity;
    }

    public void setEntity(UserInfo entity) {
        this.entity = entity;
    }

    public String getLastCommunity() {
        return lastCommunity;
    }

    public void setLastCommunity(String lastCommunity) {
        this.lastCommunity = lastCommunity;
    }
}
