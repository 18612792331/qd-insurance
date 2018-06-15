package com.qding.api.call.app.qding.v1_3_0.struct.user;

import java.io.Serializable;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.Room;

public class Invitation implements Serializable {
	
	
	private static final long serialVersionUID = -6781813068828123961L;

	/**
     * 房间信息对象
     */
	private Room room;

	/**
     * 邀请码
     */
	private String qrcode;
	
	/**
     * 身份
     */
	private Short hkIndentity;

	/**
     * 描述
     */
	private String description;
	
	/**
     * 有效起始时间
     */
	private Long validityStartAt;

	/**
     * 有效终止时间
     */
	private Long validityEndAt;

	/**
     * 创建时间
     */
	private Long createTime;
	
	public Invitation() {

	}

	public Long getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	
	/**
	 * @return the hkIndentity
	 */
	public Short getHkIndentity() {
		return hkIndentity;
	}


	/**
	 * @param hkIndentity the hkIndentity to set
	 */
	public void setHkIndentity(Short hkIndentity) {
		this.hkIndentity = hkIndentity;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getValidityStartAt() {
		return validityStartAt;
	}

	public void setValidityStartAt(Long validityStartAt) {
		this.validityStartAt = validityStartAt;
	}

	public Long getValidityEndAt() {
		return validityEndAt;
	}

	public void setValidityEndAt(Long validityEndAt) {
		this.validityEndAt = validityEndAt;
	}

}