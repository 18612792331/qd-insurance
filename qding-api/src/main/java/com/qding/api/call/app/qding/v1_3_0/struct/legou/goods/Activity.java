package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods;

import java.io.Serializable;
import java.util.List;

public class Activity implements Serializable{

	
	private static final long serialVersionUID = -6680319487588124421L;
	
	/**
	 * id
	 */
	private String id;
	
	/**
	 * 活动名称
	 */
	private String activityName;
	
	/**
	 * 
	 */
	private String navigation;
	
	/**
	 * 活动图片
	 */
	private String pic;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 可点击连接
	 */
	private String url;
	
	/**
	 * 包含的货品
	 */
	private List<String> skuIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getNavigation() {
		return navigation;
	}

	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	

	/**
	 * @return the skuIds
	 */
	public List<String> getSkuIds() {
		return skuIds;
	}

	/**
	 * @param skuIds the skuIds to set
	 */
	public void setSkuIds(List<String> skuIds) {
		this.skuIds = skuIds;
	}

	public Activity(){
		
	}
}
