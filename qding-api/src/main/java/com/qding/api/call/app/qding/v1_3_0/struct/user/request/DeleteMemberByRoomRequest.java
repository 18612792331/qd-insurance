package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class DeleteMemberByRoomRequest  extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5099601575617017838L;

	public DeleteMemberByRoomRequest() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 房间ID
	 */
	@NotNullValidate
	private String roomId;
	
	/**
	 * 会员ID
	 */
	@NotNullValidate
	private String memberId;
	
	/**
	 *成员会员ID
	 */
	@NotNullValidate
	private String guestMemberId;
	
	

	/**
	 * @return the roomId
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the guestMemberId
	 */
	public String getGuestMemberId() {
		return guestMemberId;
	}

	/**
	 * @param guestMemberId the guestMemberId to set
	 */
	public void setGuestMemberId(String guestMemberId) {
		this.guestMemberId = guestMemberId;
	}


    @Override
    public String toString() {
        return "DeleteMemberByRoomRequest [roomId="+roomId+",memberId=" + memberId+",guestMemberId="+guestMemberId+", toString()=" + super.toString() + "]";
    }

}
