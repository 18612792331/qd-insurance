package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="projectSpell")
public class ProjectSpell implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5954931409179154835L;

	private String spell;
	
	private List<Project> list;
	
	public ProjectSpell() {

	}

	public ProjectSpell(String spell, List<Project> list) {
		super();
		this.spell = spell;
		this.list = list;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public List<Project> getList() {
		return list;
	}

	public void setList(List<Project> list) {
		this.list = list;
	}

}
