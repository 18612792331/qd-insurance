package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;

@Validate
public class GetMemberRoomsRequest extends BaseRequest{

	private static final long serialVersionUID = -7984683678342356499L;

	/**
	 * 会员ID
	 */
	@NotNullValidate
	private String memberId;

	//查询身份
	private String hkIndentitys;

	public GetMemberRoomsRequest() {

	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public GetMemberRoomsRequest( String memberId) {
		super();
		this.memberId = memberId;
	}

	public String getHkIndentitys() {
		return hkIndentitys;
	}

	public void setHkIndentitys(String hkIndentitys) {
		this.hkIndentitys = hkIndentitys;
	}

	@Override
    public String toString() {
        return "GetMemberRoomsRequest [ hkIndentitys="+hkIndentitys+" , memberId="+memberId+",toString()=" + super.toString() + "]";
    }
	
}
