package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods;

import java.io.Serializable;
import java.util.List;

public class HomeRecommend implements Serializable{

	
	private static final long serialVersionUID = -1193797578916792760L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * 推荐名称
	 */
	private String name;
	
	/**
	 * 推荐商品信息
	 */
	private List<Goods> goods;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public HomeRecommend(String id, String name, List<Goods> goods) {
		super();
		this.id = id;
		this.name = name;
		this.goods = goods;
	}
	
	public HomeRecommend(){
		
	}
	
	
}
