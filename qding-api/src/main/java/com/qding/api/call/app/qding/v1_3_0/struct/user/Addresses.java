package com.qding.api.call.app.qding.v1_3_0.struct.user;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class Addresses {

	public Addresses() {
	}

	@ExplainAnnotation (explain = "地址id")
	@NotNullValidate
	private String id;

	@NotNullValidate
	@ExplainAnnotation (explain = "会员id")
	private String memberId;

	@ExplainAnnotation (explain = "社区id")
	@NotNullValidate
	private String projectId ;

	@ExplainAnnotation (explain = "社区名称")
	private String projectName;

	@NotNullValidate
	@ExplainAnnotation (explain = "业态类型:{0:通用,2:乐购,3:洗衣,4:洗车}")
	private Integer addressBusinessType;

	@ExplainAnnotation (explain = "姓名")
	private String name;

	@ExplainAnnotation (explain = "电话")
	private String mobile;

	@ExplainAnnotation (explain = "手机")
	private String phone ;

	@ExplainAnnotation (explain = "城市id")
	private String cityId ;

	@ExplainAnnotation (explain = "城市名称")
	private String cityName ;

	@ExplainAnnotation (explain = "省id")
	private String provinceId ;

	@ExplainAnnotation (explain = "省名称")
	private String provinceName ;

	@ExplainAnnotation (explain = "地址详情",desc = "新版选择其他时此字段不为空")
	private String address ;

	@ExplainAnnotation(explain = "地址详情", desc = "app显示用")
	private String addressStr;

	@ExplainAnnotation (explain = "邮编")
	private String postCode ;

	@ExplainAnnotation (explain = "性别")
	private String gender;

	@ExplainAnnotation (explain = "是否为默认地址")
	private Integer  defaultFlag = 0;

	@ExplainAnnotation (explain = "房间ID")
	private String roomId;

	@ExplainAnnotation(explain = "区县ID",desc = "2.5版本新增")
	private String areaId;

	@ExplainAnnotation (explain = "区域地址")
	private String areaName;

	@ExplainAnnotation (explain = "街道地址",desc = "2.5版本新增")
	private String street;

	@ExplainAnnotation (explain = "是否设置最新地址",desc = "0:未设置，1：已设置")
	private Integer isSetting = Integer.valueOf(0);

	@ExplainAnnotation(explain = "房屋名称",desc = "建筑物-房间")
	private String roomName;

	@ExplainAnnotation(explain = "api内部使用")
	private Integer version;

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getIsSetting() {
		return isSetting;
	}

	public void setIsSetting(Integer isSetting) {
		this.isSetting = isSetting;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return the defaultFlag
	 */
	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	/**
	 * @param defaultFlag the defaultFlag to set
	 */
	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getAddressBusinessType() {
		return addressBusinessType;
	}
	
	
	public void setAddressBusinessType(Integer addressBusinessType) {
		this.addressBusinessType = addressBusinessType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the cityId
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the provinceId
	 */
	public String getProvinceId() {
		return provinceId;
	}

	/**
	 * @param provinceId the provinceId to set
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * @return the provinceName
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * @param provinceName the provinceName to set
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getAddressStr() {
		return addressStr;
	}

	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
