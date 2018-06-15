package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetUserForReceiveMessageReuqest  extends BaseRequest  {

	
	private static final long serialVersionUID = 8495137362893495830L;

	public GetUserForReceiveMessageReuqest() {
	}
	
	/**
	 * 会员ID
	 */
	@NotNullValidate
	private String memberId;

	/**
	 * 地址业态 业态类型:{0:通用,2:乐购,3:洗衣,4:洗车}
	 */
	@NotNullValidate
	private Integer addressBusinessType;
	
	public Integer getAddressBusinessType() {
		return addressBusinessType;
	}
	
	public void setAddressBusinessType(Integer addressBusinessType) {
		this.addressBusinessType = addressBusinessType;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public GetUserForReceiveMessageReuqest(String memberId) {
		super();
		this.memberId = memberId;
	}
	
	@Override
    public String toString() {
        return "GetUserForReceiveMessageReuqest [memberId="+memberId+",toString()=" + super.toString() + "]";
    }

}
