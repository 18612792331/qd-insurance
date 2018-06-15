package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.api.smart.validate.rule.RoomValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class RoomBindingRequest  extends BaseRequest  {

	
	private static final long serialVersionUID = -6973988235781581779L;

	public RoomBindingRequest() {
	}
	
	/**
     * 会员ID
     */
	@MemberValidate
	private String memberId;
	
	/**
     * 手机号
     */
	@NotNullValidate
	private String mobile;

	/**
	 * 会员名称 1.4.1新增
	 */
	private String memberName;

	
	/**
     * 验证码
     */
	private String verifyCode;
	
	/**
     * 房间号ID
     */
	@RoomValidate
	private String roomId;
	
	/**
     * 身份
     */
	@ExplainAnnotation (explain = "1：业主，2：业主亲戚，3：业主朋友，4：租客，5,：装修负责人，6：游客，8：物业人员，9:保姆，10：司机")
	private Integer hkIndentity;
	
	/**
     * 邀请码
     */
	private String qrcode;

	/**
	 * 是否为隐藏手机号模式
	 */
	@ExplainAnnotation (explain = "0：默认，1：隐藏手机号模式")
	private Integer hideMobile = 0;


	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the qrcode
	 */
	public String getQrcode() {
		return qrcode;
	}

	/**
	 * @param qrcode the qrcode to set
	 */
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
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


	public Integer getHideMobile() {
		return hideMobile;
	}

	public void setHideMobile(Integer hideMobile) {
		this.hideMobile = hideMobile;
	}

	@Override
	public String toString() {
		return "RoomBindingRequest{" +
				"memberId='" + memberId + '\'' +
				", mobile='" + mobile + '\'' +
				", memberName='" + memberName + '\'' +
				", verifyCode='" + verifyCode + '\'' +
				", roomId='" + roomId + '\'' +
				", hkIndentity=" + hkIndentity +
				", qrcode='" + qrcode + '\'' +
				", hideMobile=" + hideMobile +
				'}';
	}
}
