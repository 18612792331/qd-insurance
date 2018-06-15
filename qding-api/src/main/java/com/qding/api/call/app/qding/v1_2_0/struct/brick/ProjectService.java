package com.qding.api.call.app.qding.v1_2_0.struct.brick;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 业态
 * @author lichao
 *
 */
@XStreamAlias(value="projectService")
public class ProjectService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3755750105604934727L;

	private String id;
	
	private String name;
	
	private Integer type;
	
	private String content;
	
	private String imageUrl;
	
	private String desc;
	
	public ProjectService() {

	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}

	public ProjectService(String id, String name, Integer type, String content,
			String imageUrl, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.content = content;
		this.imageUrl = imageUrl;
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", type=" + type
				+ ", content=" + content + ", imageUrl=" + imageUrl + ", desc="
				+ desc + "]";
	}


}
