package com.qding.api.call.app.qding.v2_3_0.struct.brick;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Project;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

@XStreamAlias(value="projectSpell")
public class ProjectsByGroup implements Serializable{


	private static final long serialVersionUID = 5954931409179154835L;

	@ExplainAnnotation (explain = "分组值",desc = "之前只是通过拼音，现在还需支持距离")
	private String groupV;

	@ExplainAnnotation (explain = "社区列表")
	private List<Project> list;
	
	public ProjectsByGroup() {

	}

	public ProjectsByGroup(String groupV, List<Project> list) {
		super();
		this.groupV = groupV;
		this.list = list;
	}

	public String getGroupV() {
		return groupV;
	}

	public void setGroupV(String groupV) {
		this.groupV = groupV;
	}

	public List<Project> getList() {
		return list;
	}

	public void setList(List<Project> list) {
		this.list = list;
	}

}
