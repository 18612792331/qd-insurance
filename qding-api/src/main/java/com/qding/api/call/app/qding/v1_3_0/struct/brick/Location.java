package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import java.io.Serializable;

public class Location implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3351297042488990582L;

	private String country;
	
	private String city;
	
	private String province;
	
	private String ip;

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Location() {

	}

	public Location(String country, String city, String province, String ip) {
		super();
		this.country = country;
		this.city = city;
		this.province = province;
		this.ip = ip;
	}

}
