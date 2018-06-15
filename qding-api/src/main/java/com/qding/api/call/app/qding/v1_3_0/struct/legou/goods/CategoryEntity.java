package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods;

import java.io.Serializable;
import java.util.List;

public class CategoryEntity extends Category implements Serializable {
	
	public CategoryEntity() {

	}
	
	public CategoryEntity(String id, String name, String icon) {
		super(id, name, icon);
	}

	private static final long serialVersionUID = 7559896541589192978L;
	
	/**
	 * 品类信息对象列表
	 */
	private List<Category> subCategory;

	public List<Category> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<Category> subCategory) {
		this.subCategory = subCategory;
	}
	
}
