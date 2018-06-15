package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods;

import java.io.Serializable;

public class Category  implements Serializable{

	private static final long serialVersionUID = -989211003247262087L;
	
	/**
	 * 品类ID
	 */
	private String id;
	
	/**
	 * 品类名称
	 */
	private String name;
	
	/**
	 * 品类图标
	 */
	private String icon;

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Category(String id, String name, String icon) {
		super();
		this.id = id;
		this.name = name;
		this.icon = icon;
	}
	
	public Category(){
		
	}

}
