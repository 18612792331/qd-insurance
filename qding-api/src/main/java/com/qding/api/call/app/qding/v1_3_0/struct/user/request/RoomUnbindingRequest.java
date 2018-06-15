package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.api.smart.validate.rule.RoomValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
@Validate
public class RoomUnbindingRequest  extends BaseRequest  {

	
	private static final long serialVersionUID = 6137994095142258469L;

	public RoomUnbindingRequest() {
	}

	/**
	 * 1.4.1 新增 用于删除审核失败的房屋绑定申请
	 */
	private String bindId;
	
	/**
     * 会员ID
     */
	@MemberValidate
	private String memberId;
	
	/**
     * 房间ID
     */
	@RoomValidate
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

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}

	public RoomUnbindingRequest(String memberId, String roomId,String bindId) {
		super();
		this.memberId = memberId;
		this.roomId = roomId;
		this.bindId = bindId;
	}
	
	@Override
    public String toString() {
        return "RoomUnbindingRequest [memberId="+memberId+",roomId="+roomId+",bindId="+bindId+",toString()=" + super.toString() + "]";
    }
    

}
