package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 报事回执
 * @author lichao
 *
 */
@XStreamAlias(value="reportReceipt")
public class ReportReceipt implements Serializable{

	private static final long serialVersionUID = 8475926297912072950L;

	private String id;
	
	private String content;
	
	private long createTime;
	
	public ReportReceipt() {

	}

	public ReportReceipt(String id, String content, long createTime) {
		super();
		this.id = id;
		this.content = content;
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ReportReceipt [id=" + id + ", content=" + content
				+ ", createTime=" + createTime + ", toString()="
				+ super.toString() + "]";
	}
	
}
