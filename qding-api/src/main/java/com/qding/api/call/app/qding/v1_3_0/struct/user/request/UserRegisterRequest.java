package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class UserRegisterRequest  extends BaseRequest  {

	private static final long serialVersionUID = -297385823946608492L;

	public UserRegisterRequest() {
	}
	
	/**
	 * 手机号
	 */
	@NotNullValidate
	private String mobile;
	
	/**
	 * 密码
	 */
	@NotNullValidate
	private String password;
	
	/**
     * 来源类型
     */
	@NotNullValidate
	private String sourceType;
	
	/**
     * 验证码
     */
	@NotNullValidate
	private String verifyCode;
	
	/**
     * 邀请码
     */
	private String qrcode = "";

	/**
	 * 当前社区ID
	 */
	private String projectId ="";

	/**
	 * 亲情支付开通角色
	 */
	private String role;

	/**
	 * 亲情支付开通者ID
	 */
	private String mid;

	/**
	 * 亲情支付开通时用户头像
	 */
	private String headImg;

	/**
	 * 亲情支付开通时名称
	 */
	private String name;

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getQrcode() {
		return qrcode;
	}


	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getVerifyCode() {
		return verifyCode;
	}
	
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserRegisterRequest(String mobile, String password, String sourceType,
							   String verifyCode, String projectId) {
		super();
		this.mobile = mobile;
		this.password = password;
		this.sourceType = sourceType;
		this.verifyCode = verifyCode;
		this.projectId = projectId;
	}
}
