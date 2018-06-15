package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 根据roomId查询物业费缴费详情					
 * @author lichao
 *
 */
@Validate
public class GetRoomPropertyBillsRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7722980109485018092L;


	private Long month;
	
	@NotNullValidate
	private String roomId;

    //兼容时区问题 yyyy-MM-dd
    private String date;

	private String memberId;

	public GetRoomPropertyBillsRequest() {

	}

	
	public GetRoomPropertyBillsRequest(Long month, String roomId,String date) {
		super();
		this.month = month;
		this.roomId = roomId;
        this.date = date;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMonth(Long month) {
		this.month = month;
	}
	
	public Long getMonth() {
		return month;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}


	@Override
	public String toString() {
		return "GetRoomPropertyBillsRequest [month=" + month + ", roomId="
				+ roomId + ", toString()=" + super.toString() + "]";
	}
	

}
