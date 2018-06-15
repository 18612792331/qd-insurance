package com.qding.api.ip;

import java.io.Serializable;

/**
 * {"code":0,"data":
 * {"country":"\u672a\u5206\u914d\u6216\u8005\u5185\u7f51IP","country_id":"IANA","area":"","area_id":"",
 * "region":"","region_id":"","city":"","city_id":"","county":"","county_id":"","isp":"","isp_id":"","ip":"127.0.0.1"}
 * }
 * 
 * @author Administrator
 *
 */
public class TaoBaoCity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 919304359822949890L;

	private String country;
	
	private String province;
	
	private String city;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public TaoBaoCity(String country, String province, String city) {
		super();
		this.country = country;
		this.province = province;
		this.city = city;
	}
	
}
