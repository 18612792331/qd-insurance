package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="group")
public class Group implements Serializable{

	private static final long serialVersionUID = -3958595739686692162L;

	@ExplainAnnotation (explain = "组团Code")
	private String groupCode;

	@ExplainAnnotation (explain = "组团名称")
	private String groupName;

	public Group() {

	}
	
	public Group(String groupCode, String groupName) {
		super();
		this.groupCode = groupCode;
		this.groupName = groupName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return "Group [groupCode=" + groupCode + ", groupName=" + groupName
				+ "]";
	}
	
}
