package com.qding.api.call.app.qding.v3_3_0.struct.neighbor;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

public class FavoriteDto extends SkipUrl implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExplainAnnotation (explain = "帖子ID")
    private String id;

    @ExplainAnnotation (explain = "比如百科的兴趣分类，新闻分类")
    private String favoriteType;

    @ExplainAnnotation (explain = "标题")
    private String title;

    @ExplainAnnotation (explain = "图片")
    private String imgUrl;

    @ExplainAnnotation(explain = "时间")
    private Long publishTime;
    
    @ExplainAnnotation(explain = "描述")
    private String desc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFavoriteType() {
		return favoriteType;
	}

	public void setFavoriteType(String favoriteType) {
		this.favoriteType = favoriteType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}
	

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "FavoriteDto [id=" + id + 
				", favoriteType=" + favoriteType
				+ ", title=" + title + 
				", imgUrl=" + imgUrl +
				", publishTime="
				+ publishTime + "]";
	}

	 
	
    
    
    
    
    
    
    
    
    

}
