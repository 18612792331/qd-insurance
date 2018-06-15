package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetAllMemberByRoomRequest  extends BaseRequest  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4117507621578001781L;

	public GetAllMemberByRoomRequest() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 会员ID
	 */
	@NotNullValidate
	private String memberId;
	
	/**
	 * 房间ID
	 */
	@NotNullValidate
	private String roomId;


	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public GetAllMemberByRoomRequest( String memberId,
			String roomId) {
		super();
		this.memberId = memberId;
		this.roomId = roomId;
	}
	
	 @Override
     public String toString() {
         return "GetAllMemberByRoomRequest [memberId="+memberId+",roomId=" + roomId+",toString()=" + super.toString() + "]";
     }

}
