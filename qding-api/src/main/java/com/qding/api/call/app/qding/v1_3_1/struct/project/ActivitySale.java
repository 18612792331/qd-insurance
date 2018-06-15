package com.qding.api.call.app.qding.v1_3_1.struct.project;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="activitySale")
public class ActivitySale implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8664579249234149760L;

	private String title;
	
	private String desc;
	
	private String imageUrl;

	
	/**
	 * 跳转类型
	 */
	private int type;
	
	/**
	 * 实体ID
	 */
	private String id;
	
	/**
	 * 跳转URL
	 */
	private String url;
	
	
	public ActivitySale() {

	}

	

	public ActivitySale(String title, String desc, String imageUrl, int type,
			String id, String url) {
		super();
		this.title = title;
		this.desc = desc;
		this.imageUrl = imageUrl;
		this.type = type;
		this.id = id;
		this.url = url;
	}




	@Override
	public String toString() {
		return "ActivitySale [title=" + title + ", desc=" + desc
				+ ", imageUrl=" + imageUrl + ", type=" + type + ", id=" + id
				+ ", url=" + url + "]";
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}
}
