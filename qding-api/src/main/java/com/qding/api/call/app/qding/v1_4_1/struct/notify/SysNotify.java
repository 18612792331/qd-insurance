package com.qding.api.call.app.qding.v1_4_1.struct.notify;

import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

public class SysNotify extends SkipUrl implements Serializable{

	private static final long serialVersionUID = -3789023608341933868L;

	private String id;
	
	private String title;
	
	private Long createTime;
	
	private String content;

	public SysNotify() {
	}

	public SysNotify(String id, String title, Long createTime, String content) {
		this.id = id;
		this.title = title;
		this.createTime = createTime;
		this.content = content;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}



}
