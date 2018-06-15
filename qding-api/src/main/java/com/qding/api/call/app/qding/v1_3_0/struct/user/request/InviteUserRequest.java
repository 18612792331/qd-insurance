package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.smart.validate.rule.AccountValidate;
import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.api.smart.validate.rule.ProjectValidate;
import com.qding.api.smart.validate.rule.RoomValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class InviteUserRequest  extends BaseRequest  {

	
	private static final long serialVersionUID = -8041737236691646046L;

	public InviteUserRequest() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     * 账户ID
     */
	@AccountValidate
	private String accountId; 
	
	/**
     * 会员ID
     */
	@MemberValidate
	private String memberId;
	
	/**
     * 社区ID
     */
	@ProjectValidate
	private String projectId;
	
	/**
     * 房间ID
     */
	@RoomValidate
	private String roomId;
	
	/**
     * 身份
     */
	@NotNullValidate
	@ExplainAnnotation(explain = "1：业主，2：家庭成员，3：业主朋友，4：租客，5,：装修负责人，6：游客，8：物业人员，9:保姆，10：司机")
	private Integer hkIndentity;
	
	/**
     * 有效时间
     */
	@NotNullValidate
	private Integer validityTime;
	
	/**
     * 会员名称
     */
	@NotNullValidate
	private String name;
	
	/**
     * 描述
     */
	@NotNullValidate
	private String desc;



	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Integer getHkIndentity() {
		return hkIndentity;
	}

	public void setHkIndentity(Integer hkIndentity) {
		this.hkIndentity = hkIndentity;
	}


	public Integer getValidityTime() {
		return validityTime;
	}
	
	public void setValidityTime(Integer validityTime) {
		this.validityTime = validityTime;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public InviteUserRequest(String accountId, String memberId,
			String projectId, String roomId, Integer hkIndentity,
			Integer validityTime, String name, String desc) {
		super();
		this.accountId = accountId;
		this.memberId = memberId;
		this.projectId = projectId;
		this.roomId = roomId;
		this.hkIndentity = hkIndentity;
		this.validityTime = validityTime;
		this.name = name;
		this.desc = desc;
	}


	@Override
    public String toString() {
        return "InviteUserRequest [memberId="+memberId+",projectId="+projectId+",accountId="+accountId+",roomId="+roomId+","
                + "hkIndentity="+hkIndentity+",validityTime="+validityTime+",name="+name+", desc="+ desc+",toString()=" + super.toString() + "]";
    }

	

}
