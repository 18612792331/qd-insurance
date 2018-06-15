package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="illegalNotify")
public class IllegalNotify implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4721423041665620110L;

	/**
	 * 通知图片
	 */
	private String notifyImageUrl;
	
	/**
	 * 通知内容
	 */
	private String notifyContent;
	
	/**
	 * 通知时间
	 */
	private long notifyTime;
	
	public IllegalNotify() {

	}

	public IllegalNotify(String notifyImageUrl, String notifyContent,
			long notifyTime) {
		super();
		this.notifyImageUrl = notifyImageUrl;
		this.notifyContent = notifyContent;
		this.notifyTime = notifyTime;
	}

	public void setNotifyImageUrl(String notifyImageUrl) {
		this.notifyImageUrl = notifyImageUrl;
	}
	
	public String getNotifyImageUrl() {
		return notifyImageUrl;
	}

	public String getNotifyContent() {
		return notifyContent;
	}

	public void setNotifyContent(String notifyContent) {
		this.notifyContent = notifyContent;
	}

	public long getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(long notifyTime) {
		this.notifyTime = notifyTime;
	}

	@Override
	public String toString() {
		return "SystemNotify [notifyImageUrl=" + notifyImageUrl + ", notifyContent="
				+ notifyContent + ", notifyTime=" + notifyTime + "]";
	}
	
}
