package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="citySpell")
public class CitySpell implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5954931409179154835L;

	private String spell;
	
	private List<City> list;
	
	public CitySpell() {

	}

	public CitySpell(String spell, List<City> list) {
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

	public List<City> getList() {
		return list;
	}

	public void setList(List<City> list) {
		this.list = list;
	}
}
