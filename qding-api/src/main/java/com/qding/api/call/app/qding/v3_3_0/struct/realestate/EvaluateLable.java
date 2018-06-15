package com.qding.api.call.app.qding.v3_3_0.struct.realestate;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;

/**
 * @Description:评价标签
 * @author 行者
 * @date 2017年12月4日 上午11:50:10 
 * @version V1.0
 */
public class EvaluateLable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6688719437895515399L;

	@ExplainAnnotation (explain = "评价标签id")
	private String lableId;
	
	@ExplainAnnotation (explain = "评价标签名")
	private String lableName;
	
	@ExplainAnnotation (explain = "评价星级")
    private int starLevel;

	public String getLableId() {
		return lableId;
	}

	public void setLableId(String lableId) {
		this.lableId = lableId;
	}

	public String getLableName() {
		return lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
	
	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	@Override
	public String toString() {
		return "EvaluateLable [lableId=" + lableId + ", lableName=" + lableName
				+ "]";
	}
	
	

	
	
	
	
		
}
