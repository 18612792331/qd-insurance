package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 标签
 * @author lichao
 *
 */
@XStreamAlias(value="tag")
public class Tag implements	Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6883752684845662848L;

	/**
	 * 标签ID
	 */
	private String tagId;
	
	/**
	 * 标签名称
	 */
	private String tagName;
	
	/**
	 * 标签信息发布数
	 */
	private Integer tagCount;
	
	
	public Tag() {

	}
	
	public Tag(String tagId, String tagName, Integer tagCount) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
		this.tagCount = tagCount;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getTagCount() {
		return tagCount;
	}
	
	public void setTagCount(Integer tagCount) {
		this.tagCount = tagCount;
	}
	
	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", tagName=" + tagName + ", tagCount="
				+ tagCount + "]";
	}
}
