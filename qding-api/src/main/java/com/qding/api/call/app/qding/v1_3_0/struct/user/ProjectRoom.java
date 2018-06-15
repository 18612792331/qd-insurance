package com.qding.api.call.app.qding.v1_3_0.struct.user;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Room;

public class ProjectRoom implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -2541126542136928425L;

	/**
     * 房屋信息对象
     */
	private Room room;
	
	/**
     * 身份
     */
	@ExplainAnnotation (explain = "身份标示")
	private Short hkIndentity;
	
	@ExplainAnnotation (explain = "身份名称",desc="3.2新增")
	private String roleName;

	/**
     * 有效起始时间
     */
	private Long validityStartAt;

	/**
	 * 有效终止时间
	 */
	private Long validityEndAt;

	/**
	 * 审核状态 0:待审核  1：审核通过  2：审核不通过
	*/
	private Integer auditStatus;

	@ExplainAnnotation (explain = "申请房屋绑定ID",desc="qding_passport.room_bind_apply表的id")
	private String bindId;
	
	@ExplainAnnotation (explain = "1:上传身份证申请 2:业主绑定房屋申请",desc="3.2新增")
	private Integer applyType;
	

	public ProjectRoom() {

	}

	public ProjectRoom(Room room, Short hkIndentity, Long validityStartAt,
			Long validityEndAt,Integer auditStatus,String bindId) {
		super();
		this.room = room;
		this.hkIndentity = hkIndentity;
		this.validityStartAt = validityStartAt;
		this.validityEndAt = validityEndAt;
		this.auditStatus = auditStatus;
		this.bindId = bindId;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
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

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
}
