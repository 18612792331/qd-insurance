package com.qding.api.call.app.qding.v3_2_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class AddAdressReuqest extends BaseRequest {


    private static final long serialVersionUID = -2683371457045882944L;

    public AddAdressReuqest() {

    }
    
    @ExplainAnnotation(explain = "社区id")
    private String projectId;

    @NotNullValidate
    @ExplainAnnotation(explain = "业态类型:{0:通用,2:乐购,3:洗衣,4:洗车}")
    private Integer addressBusinessType;

    @ExplainAnnotation(explain = "姓名")
    private String name;

    @ExplainAnnotation(explain = "电话")
    private String mobile;

    @ExplainAnnotation(explain = "是否为默认地址")
    private Integer defaultFlag = 0;

    @ExplainAnnotation(explain = "房间ID")
    private String roomId;

    //---------------------------------------------
    
    @ExplainAnnotation(explain = "省id", desc="所在社区没开通时用的着")
    private String provinceId;

    @ExplainAnnotation(explain = "省名称", desc="所在社区没开通时用的着")
    private String provinceName;
    
    @ExplainAnnotation(explain = "城市id", desc="所在社区没开通时用的着")
    private String cityId;

    @ExplainAnnotation(explain = "城市名称", desc="所在社区没开通时用的着")
    private String cityName;

    @ExplainAnnotation(explain = "区县ID", desc="所在社区没开通时用的着")
    private String areaId;
    
    @ExplainAnnotation(explain = "区域地址")
    private String areaName;
    
    @ExplainAnnotation(explain = "地址详情", desc="所在社区没开通时用的着")
    private String address;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getAddressBusinessType() {
		return addressBusinessType;
	}

	public void setAddressBusinessType(Integer addressBusinessType) {
		this.addressBusinessType = addressBusinessType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

    
    
     
}
