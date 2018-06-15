package com.qding.api.call.app.qding.v2_3_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 根据城市id获取社区列表					
 * @author lichao
 *
 */
@Validate
public class GetProjectByCityIdRequest extends BaseRequest{

	private static final long serialVersionUID = 8783719634823278835L;

	@NotNullValidate
	@ExplainAnnotation (explain = "城市ID")
	private String cityId;

	@ExplainAnnotation (explain = "纬度")
	private String latitude;

	@ExplainAnnotation (explain = "经度")
	private String longitude;

    private Integer locationType;

    public Integer getLocationType() {
        return locationType;
    }

    public void setLocationType(Integer locationType) {
        this.locationType = locationType;
    }

    public GetProjectByCityIdRequest() {

	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

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
		return "GetProjectByCityIdRequest{" +
				"cityId='" + cityId + '\'' +
				", latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", locationType=" + locationType +
				'}';
	}
}
