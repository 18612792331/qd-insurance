package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request;

import com.qding.api.smart.validate.rule.AccountValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 门禁预约申请					
 * @author lichao
 *
 */
@Validate
public class ApplyAccessControlRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -673256963068415218L;

	/**
	 * 用户ID
	 */
	@AccountValidate
	private String accountId;

	/**
	 * 房间ID
	 */
	@NotNullValidate
	private String roomId;

	/**
	 * 访客名
	 */
	@NotNullValidate
	private String name;

	/**
	 * 手机号
	 */
	@NotNullValidate
	private String phone;

	/**
	 * 访客目的 0 朋友来访 1 家政服务 2 装修放行 3 送货上门 4 搬家放行 5我的钥匙
	 */
	@NotNullValidate
	private Integer purposeType;

	/**
	 * 通行时间 1 今天 2 明天 3 后天
	 */
	@NotNullValidate
	private Integer purposeDateType;

	/**
	 * 通行类型 APP 1.4.1 新增  1:二维码 2：蓝牙 3：两者都支持
	 */
	private Integer accessType ;

	public ApplyAccessControlRequest() {
	}


	public ApplyAccessControlRequest(String accountId, String roomId,
			String name, String phone, Integer purposeType,
			Integer purposeDateType,Integer accessType) {
		super();
		this.accountId = accountId;
		this.roomId = roomId;
		this.name = name;
		this.phone = phone;
		this.purposeType = purposeType;
		this.purposeDateType = purposeDateType;
		this.accessType = accessType;
	}


	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getPurposeType() {
		return purposeType;
	}

	public void setPurposeType(Integer purposeType) {
		this.purposeType = purposeType;
	}

	public Integer getPurposeDateType() {
		return purposeDateType;
	}

	public void setPurposeDateType(Integer purposeDateType) {
		this.purposeDateType = purposeDateType;
	}

	public Integer getAccessType() {
		return accessType;
	}

	public void setAccessType(Integer accessType) {
		this.accessType = accessType;
	}

	@Override
	public String toString() {
		return "ApplyAccessControlRequest [accountId=" + accountId + ", roomId="
				+ roomId +  ", name=" + name
				+ ", phone=" + phone + ", purposeType=" + purposeType
				+ ", purposeDateType=" + purposeDateType + " ,accessType="+accessType+", toString()="
				+ super.toString() + "]";
	}
	
}
