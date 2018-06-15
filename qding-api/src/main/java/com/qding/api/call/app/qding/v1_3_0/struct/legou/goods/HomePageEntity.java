package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods;

import java.util.List;

public class HomePageEntity {
	
	/**
	 * 品类对象列表
	 */
	private List<CategoryEntity> category;
	
	/**
	 * 推荐货品信息列表
	 */
	private List<HomeRecommend> recommend;
	
	/**
	 * 活动信息列表
	 */
	private List<Activity> activity;

	
	public List<HomeRecommend> getRecommend() {
		return recommend;
	}

	public void setRecommend(List<HomeRecommend> recommend) {
		this.recommend = recommend;
	}

	public List<Activity> getActivity() {
		return activity;
	}

	public void setActivity(List<Activity> activity) {
		this.activity = activity;
	}
	
	/**
	 * @return the category
	 */
	public List<CategoryEntity> getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(List<CategoryEntity> category) {
		this.category = category;
	}

	public HomePageEntity(){
		
	}

}
