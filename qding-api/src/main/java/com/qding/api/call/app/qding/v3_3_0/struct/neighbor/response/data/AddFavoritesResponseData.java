package com.qding.api.call.app.qding.v3_3_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/2/23.
 */
public class AddFavoritesResponseData extends ResponseData {

    private static final long serialVersionUID = 2108010926604691864L;

    @ExplainAnnotation (explain = "总收藏量")
    private Integer favoriteNum=0;

	public Integer getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(Integer favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	@Override
	public String toString() {
		return "AddFavoritesResponseData [favoriteNum=" + favoriteNum + "]";
	}
    
    
    
    
}
