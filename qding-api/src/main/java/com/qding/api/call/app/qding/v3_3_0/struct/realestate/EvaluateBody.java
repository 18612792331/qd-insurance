package com.qding.api.call.app.qding.v3_3_0.struct.realestate;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

/**
 * @Description:评价主体 
 * @author 行者
 * @date 2017年12月4日 上午11:50:10 
 * @version V1.0
 */
public class EvaluateBody extends SkipUrl implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6688719437895515399L;

	@ExplainAnnotation (explain = "评价主体id")
	private String bodyId;
	
	@ExplainAnnotation (explain = "评价主体类型  0 签约，1 入住")
	private String bodyType;
	
	@ExplainAnnotation (explain = "房屋地址")
	private String roomAddress;
	
	@ExplainAnnotation (explain = "星级评分")
	private int starScore;
	
	@ExplainAnnotation (explain = "是否已经评论过0 否，1是")
	private int status;

	public String getBodyId() {
		return bodyId;
	}

	public void setBodyId(String bodyId) {
		this.bodyId = bodyId;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public String getRoomAddress() {
		return roomAddress;
	}

	public void setRoomAddress(String roomAddress) {
		this.roomAddress = roomAddress;
	}
	
	public int getStarScore() {
		return starScore;
	}

	public void setStarScore(int starScore) {
		this.starScore = starScore;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EvaluateBody [bodyId=" + bodyId + ", bodyType=" + bodyType
				+ ", roomAddress=" + roomAddress + "]";
	}
	
	
	
	
	
		
}
