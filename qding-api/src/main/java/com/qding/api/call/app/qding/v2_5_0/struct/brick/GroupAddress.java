package com.qding.api.call.app.qding.v2_5_0.struct.brick;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Group;

/**
 * Created by qd on 2016/11/22.
 */
public class GroupAddress extends Group {

    @ExplainAnnotation(explain = "组团地址ID")
    private String groupId;

    @ExplainAnnotation (explain = "组团街道地址")
    private String groupAddress;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupAddress() {
		return groupAddress;
	}

	public void setGroupAddress(String groupAddress) {
		this.groupAddress = groupAddress;
	}

	@Override
	public String toString() {
		return "GroupAddress [groupId=" + groupId + ", groupAddress="
				+ groupAddress + "]";
	}

    
}
