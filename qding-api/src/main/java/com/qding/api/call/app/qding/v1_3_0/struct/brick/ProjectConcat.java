package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="projectConcat")
public class ProjectConcat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6874993527042711941L;

	private String id;
	
	//电话类型
	private String type;
	
	//电话
	//TODO 下一版本删除
	private String phone;
	
	//电话 fix bug
	private String[] phones;
		
	//地址
	private String address;
	
	//邮编
	private String postCode;
	
	public ProjectConcat() {

	}

	public ProjectConcat(String id, String type, String phone, String[] phones, String address,
			String postCode) {
		super();
		this.id = id;
		this.type = type;
		this.phone = phone;
		this.phones = phones;
		this.address = address;
		this.postCode = postCode;
	}

	public String[] getPhones() {
		return phones;
	}
	
	public void setPhones(String[] phones) {
		this.phones = phones;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Override
	public String toString() {
		return "ProjectConcat [id=" + id + ", type=" + type + ", phone="
				+ phone + ", address=" + address + ", postCode=" + postCode
				+ "]";
	}

	
}
