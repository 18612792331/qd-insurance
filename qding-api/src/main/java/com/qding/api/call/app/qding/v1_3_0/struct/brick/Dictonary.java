package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="dictonary")
public class Dictonary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3030617776179305176L;

	private String key;
	
	private String value;
	
	public Dictonary() {

	}

	public Dictonary(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Dictonary [key=" + key + ", value=" + value + "]";
	}
	
}
