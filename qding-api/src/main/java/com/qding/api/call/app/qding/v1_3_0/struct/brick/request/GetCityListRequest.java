package com.qding.api.call.app.qding.v1_3_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;

/**
 * 获取城市列表		
 * @author lichao
 *
 */
@Validate
public class GetCityListRequest extends BaseRequest{

	private static final long serialVersionUID = 5331084962796042718L;


	@ExplainAnnotation(explain = "纬度")
	private String latitude;

	@ExplainAnnotation (explain = "经度")
	private String longitude;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	@Override
	public String toString() {
		return "GetCityListRequest{" +
				"latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				'}';
	}

	public GetCityListRequest() {

	}

}
