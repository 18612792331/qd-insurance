package com.qding.api.call.app.qding.v1_3_1.struct.brick;


import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * 指定社区下可用业态
 * @author jiawenzhegn
 *
 */
public class ProjectService implements Serializable{

	private static final long serialVersionUID = 3755750105604934727L;

	private String content;

	@ExplainAnnotation(explain = "服务属性名称")
	private String name;

	@ExplainAnnotation (explain = "针对报事服务,1:呼叫中心模式 2：直联模式 | 商品列表 0：列表 1：缩略图",desc = "报事服务：直联模式只针对龙湖")
	private String subType;

	@ExplainAnnotation(explain = "服务描述",desc = "针对门禁，存放身份权限")
	private String serviceDesc;

	public ProjectService() {

	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ProjectService{" +
				"content='" + content + '\'' +
				", name='" + name + '\'' +
				", subType='" + subType + '\'' +
				", serviceDesc='" + serviceDesc + '\'' +
				'}';
	}
}
