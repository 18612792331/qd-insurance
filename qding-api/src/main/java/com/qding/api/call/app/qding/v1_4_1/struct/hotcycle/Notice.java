package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 管家notice
 * @author lichao
 *
 */
@XStreamAlias(value="notice")
public class Notice extends SkipUrl implements Serializable{

	private static final long serialVersionUID = -3328396128994037755L;

	@ExplainAnnotation(explain = "公告类型",desc = "1紧急通知2通知3社区活动 6:温馨提示 7：新闻  -1: 群组公告")
	private int noticeType = -1;

	@ExplainAnnotation(explain = "公告ID")
	private String billboardId;

	@ExplainAnnotation(explain = "公告名称")
	private String name;

	@ExplainAnnotation(explain = "公告内容")
	private String content;

	@ExplainAnnotation(explain = "公告简介")
	private String desc = "";

	@ExplainAnnotation(explain = "创建时间")
	private Long createTime;

	@ExplainAnnotation(explain = "新建用户名称")
	private String createAt;

	@ExplainAnnotation(explain = "公告图片")
	private String[] imgs;

	@ExplainAnnotation (explain = "是否可分享",desc = "1:可分享，0：不可分享")
	private Integer isShare = Integer.valueOf(0);

	@ExplainAnnotation (explain = "是否在公告下方显示广告图片",desc = "1:显示，0：不显示")
	private Integer isShowFootImage = Integer.valueOf(0);

	@ExplainAnnotation (explain = "公告阅读数",desc = "3.0新增")
	private Integer readCount;

	@ExplainAnnotation (explain = "公告是否有用",desc = "3.0新增")
	private Integer userDef;

	public Integer getIsShare() {
		return isShare;
	}

	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
	}

	public Integer getIsShowFootImage() {
		return isShowFootImage;
	}

	public void setIsShowFootImage(Integer isShowFootImage) {
		this.isShowFootImage = isShowFootImage;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(int noticeType) {
		this.noticeType = noticeType;
	}

	public String getBillboardId() {
		return billboardId;
	}

	public void setBillboardId(String billboardId) {
		this.billboardId = billboardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String[] getImgs() {
		return imgs;
	}

	public void setImgs(String[] imgs) {
		this.imgs = imgs;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getUserDef() {
		return userDef;
	}

	public void setUserDef(Integer userDef) {
		this.userDef = userDef;
	}
}
