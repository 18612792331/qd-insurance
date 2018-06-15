package com.qding.api.call.app.qding.v1_3_0.struct.user;

import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class AddressesDto {

	public AddressesDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 会员id	必填
	 */
	@NotNullValidate
	private String memberId;
	
	/**
	 * 社区id
	 */
	@NotNullValidate
	private String projectId ;
	/**
	 * 业态类型:{0:通用,2:乐购,3:洗衣,4:洗车}
	 */
	@NotNullValidate
	private String businessType;	
	
	/**
	 *  姓名	可填
	 */
	private String name;
	
	/**
	 *  电话	
	 */
	private String mobile; 	
	
	/**
	 * 手机	
	 */
	private String phone ; 
	
	/**
	 * 城市id	
	 */
	private String cityId ;
	
	/**
	 * 城市名称
	 */
	private String cityName ;
	
	/**
	 * 省id
	 */
	private String provinceId ;
	
	/**
	 * 省名称
	 */
	private String provinceName ;
	
	/**
	 * 地址详情
	 */
	private String address ; 
	
	/**
	 * 邮编
	 */
	private String postCode ; 
	
	/**
	 * 性别
	 */
	private String gender;


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

	/**
	 * @return the businessType
	 */
	public String getBusinessType() {
		return businessType;
	}

	/**
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
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

	
	



}
