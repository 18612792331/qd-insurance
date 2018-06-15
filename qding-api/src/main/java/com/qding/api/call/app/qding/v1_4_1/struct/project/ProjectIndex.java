package com.qding.api.call.app.qding.v1_4_1.struct.project;

import com.google.common.collect.Lists;
import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v1_3_1.struct.project.ActivitySale;
import com.qding.api.call.app.qding.v1_3_1.struct.project.RecommendTag;

import java.io.Serializable;
import java.util.List;

public class ProjectIndex implements Serializable{


	private static final long serialVersionUID = -143257845235318152L;
	
	/**
	 * 图文banner
	 */
	private List<ActivityBanner> activityBannerFloor1;
	
	/**
	 * 图文banner
	 */
	private List<ActivityBanner> activityBannerFloor2;
	
	/**
	 * 业态
	 */
	private List<ProjectService> projectServices;

	/**
	 * 推荐的邻聚标签
	 */
	private List<RecommendTag> recommendTags;
	
	/**
	 * 社区促销活动
	 */
	private List<ActivitySale> activitySaleFloor1;
	
	/**
	 * 社区促销活动
	 */
	private List<ActivitySale> activitySaleFloor2;

	/**
	 * 社区促销活动
	 */
	private List<ActivitySale> activitySaleFloor3;

	/**
	 * 旅游活动
	 */
	private ActivitySale travelActivity;

	/**
	 * 竞拍活动（一元夺宝）
	 */
	private AuctionActivity auctionActivity;

	/**
	 * 秒杀活动
	 */
	private ActivitySale miaoshaActivity;

	/**
	 * 团购活动
	 */
	private GrouponActivity grouponActivity;

	/**
	 * 管家电话
	 */
	private List<String> phones = Lists.newArrayList();

	public ProjectIndex(List<ActivityBanner> activityBannerFloor1,
						List<ActivityBanner> activityBannerFloor2,
						List<ProjectService> projectServices,
						List<RecommendTag> recommendTags,
						List<ActivitySale> activitySaleFloor1,
						List<ActivitySale> activitySaleFloor2,
						List<ActivitySale> activitySaleFloor3,
						List<String> phones,
						ActivitySale travelActivity,
						AuctionActivity auctionActivity,
						ActivitySale miaoshaActivity,
						GrouponActivity grouponActivity
						) {
		this.activityBannerFloor1 = activityBannerFloor1;
		this.activityBannerFloor2 = activityBannerFloor2;
		this.projectServices = projectServices;
		this.recommendTags = recommendTags;
		this.activitySaleFloor1 = activitySaleFloor1;
		this.activitySaleFloor2 = activitySaleFloor2;
		this.activitySaleFloor3 = activitySaleFloor3;
		this.phones = phones;
		this.travelActivity = travelActivity;
		this.auctionActivity = auctionActivity;
		this.miaoshaActivity = miaoshaActivity;
		this.grouponActivity = grouponActivity;
	}

	public ActivitySale getTravelActivity() {
		return travelActivity;
	}

	public void setTravelActivity(ActivitySale travelActivity) {
		this.travelActivity = travelActivity;
	}

	public AuctionActivity getAuctionActivity() {
		return auctionActivity;
	}

	public void setAuctionActivity(AuctionActivity auctionActivity) {
		this.auctionActivity = auctionActivity;
	}

	public ActivitySale getMiaoshaActivity() {
		return miaoshaActivity;
	}

	public void setMiaoshaActivity(ActivitySale miaoshaActivity) {
		this.miaoshaActivity = miaoshaActivity;
	}

	public GrouponActivity getGrouponActivity() {
		return grouponActivity;
	}

	public void setGrouponActivity(GrouponActivity grouponActivity) {
		this.grouponActivity = grouponActivity;
	}

	public List<ActivityBanner> getActivityBannerFloor1() {
		return activityBannerFloor1;
	}

	public void setActivityBannerFloor1(List<ActivityBanner> activityBannerFloor1) {
		this.activityBannerFloor1 = activityBannerFloor1;
	}

	public List<ActivityBanner> getActivityBannerFloor2() {
		return activityBannerFloor2;
	}

	public void setActivityBannerFloor2(List<ActivityBanner> activityBannerFloor2) {
		this.activityBannerFloor2 = activityBannerFloor2;
	}

	public List<ProjectService> getProjectServices() {
		return projectServices;
	}

	public void setProjectServices(List<ProjectService> projectServices) {
		this.projectServices = projectServices;
	}

	public List<RecommendTag> getRecommendTags() {
		return recommendTags;
	}

	public void setRecommendTags(List<RecommendTag> recommendTags) {
		this.recommendTags = recommendTags;
	}

	public List<ActivitySale> getActivitySaleFloor1() {
		return activitySaleFloor1;
	}

	public void setActivitySaleFloor1(List<ActivitySale> activitySaleFloor1) {
		this.activitySaleFloor1 = activitySaleFloor1;
	}

	public List<ActivitySale> getActivitySaleFloor2() {
		return activitySaleFloor2;
	}

	public void setActivitySaleFloor2(List<ActivitySale> activitySaleFloor2) {
		this.activitySaleFloor2 = activitySaleFloor2;
	}

	public List<ActivitySale> getActivitySaleFloor3() {
		return activitySaleFloor3;
	}

	public void setActivitySaleFloor3(List<ActivitySale> activitySaleFloor3) {
		this.activitySaleFloor3 = activitySaleFloor3;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public ProjectIndex() {

	}


}
