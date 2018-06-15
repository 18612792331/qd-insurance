package com.qding.api.call.app.qding.v1_2_1.struct.housekeeper.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v1_2_1.struct.housekeeper.HKCycleBanner;
import com.qding.api.struct.ResponseData;

public class GetHKIndexResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8505693784159846717L;

	/**
	 * 图文banner
	 */
	private List<HKCycleBanner> banners;
	
	/**
	 * 业态
	 */
	private List<ProjectService> services;
	
	private Long currentTimeMillis;
	
	public GetHKIndexResponseData() {

	}


	public GetHKIndexResponseData(List<HKCycleBanner> banners,
			List<ProjectService> services, Long currentTimeMillis) {
		super();
		this.banners = banners;
		this.services = services;
		this.currentTimeMillis = currentTimeMillis;
	}


	public void setCurrentTimeMillis(Long currentTimeMillis) {
		this.currentTimeMillis = currentTimeMillis;
	}
	
	public Long getCurrentTimeMillis() {
		return currentTimeMillis;
	}
	
	public List<HKCycleBanner> getBanners() {
		return banners;
	}

	public void setBanners(List<HKCycleBanner> banners) {
		this.banners = banners;
	}

	public List<ProjectService> getServices() {
		return services;
	}

	public void setServices(List<ProjectService> services) {
		this.services = services;
	}


	@Override
	public String toString() {
		return "GetHKIndexResponseData [banners=" + banners + ", services="
				+ services + ", currentTimeMillis=" + currentTimeMillis
				+ ", toString()=" + super.toString() + "]";
	}

}
