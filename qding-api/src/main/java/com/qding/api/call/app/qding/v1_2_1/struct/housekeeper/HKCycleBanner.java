package com.qding.api.call.app.qding.v1_2_1.struct.housekeeper;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 管家banner
 * @author lichao
 *
 */
@XStreamAlias(value="hkCycleBanner")
public class HKCycleBanner implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3328396128994037755L;

	private long id;
	
	//标题
	private String title = "";

	//公告类型 1紧急通知2通知3社区活动  ,4详情5url
	private int noticeType = 1;
	
	private long createAt;

    public HKCycleBanner() {

    }

	public HKCycleBanner(long id, String title, int noticeType, long createAt) {
		super();
		this.id = id;
		this.title = title;
		this.noticeType = noticeType;
		this.createAt = createAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(int noticeType) {
		this.noticeType = noticeType;
	}

	public long getCreateAt() {
		return createAt;
	}

	public void setCreateAt(long createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "HKCycleBanner [id=" + id + ", title=" + title + ", noticeType="
				+ noticeType + ", createAt=" + createAt + "]";
	}

}
