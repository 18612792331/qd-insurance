package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="city")
public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6405415749299416896L;

	private String id;
	
	private String name;

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + "]";
	}

	public City() {

	}
	
	public City(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
