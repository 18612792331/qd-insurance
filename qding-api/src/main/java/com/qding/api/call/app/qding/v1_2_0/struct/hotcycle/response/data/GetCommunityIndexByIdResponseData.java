package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.CommunitySale;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.CycleBanner;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.PushTag;
import com.qding.api.struct.ResponseData;

/**
 * 根据社区id获取首页信息
 * @author lichao
 *
 */
public class GetCommunityIndexByIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6043703143800217198L;

	/**
	 * 图文banner
	 */
	private List<CycleBanner> banners;
	
	/**
	 * 业态
	 */
	private List<ProjectService> services;

	/**
	 * 标签类推送列表
	 */
	private List<PushTag> pushTags;
	
	/**
	 * 社区促销活动
	 */
	private List<CommunitySale> communitySales;
	
	public GetCommunityIndexByIdResponseData() {
		
	}


	public GetCommunityIndexByIdResponseData(List<CycleBanner> banners,
			List<ProjectService> services, List<PushTag> pushTags,
			List<CommunitySale> communitySales) {
		super();
		this.banners = banners;
		this.services = services;
		this.pushTags = pushTags;
		this.communitySales = communitySales;
	}

	public List<CommunitySale> getCommunitySales() {
		return communitySales;
	}

	public void setCommunitySales(List<CommunitySale> communitySales) {
		this.communitySales = communitySales;
	}

	public List<PushTag> getPushTags() {
		return pushTags;
	}
	
	public void setPushTags(List<PushTag> pushTags) {
		this.pushTags = pushTags;
	}
	
	public List<CycleBanner> getBanners() {
		return banners;
	}


	public void setBanners(List<CycleBanner> banners) {
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
		return "GetCommunityIndexByIdResponseData [banners=" + banners
				+ ", services=" + services + ", pushTags=" + pushTags
				+ ", communitySales=" + communitySales + ", toString()="
				+ super.toString() + "]";
	}

}
