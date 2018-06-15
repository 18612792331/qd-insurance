package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetNoticeByIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2077528826325635458L;

	@NotNullValidate
	private String noticeId;
	
	public GetNoticeByIdRequest() {
	}

	public GetNoticeByIdRequest(String noticeId) {
		super();
		this.noticeId = noticeId;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	@Override
	public String toString() {
		return "GetBannerByIdRequest [noticeId=" + noticeId + ", toString()="
				+ super.toString() + "]";
	}
	
}
