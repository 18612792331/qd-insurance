package com.qding.api.call.app.qding.v1_2_1.struct.hotcycle;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 图文banner
 * @author lichao
 *
 */
@XStreamAlias(value="cycleBanner")
public class CycleBanner implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3328396128994037755L;

	private long id;
	
	//标题
	private String title = "";

	//公告类型 1紧急通知2通知3社区活动  ,4详情5url
	private int noticeType = 1;
	
	private String pic = "";

    private String url;
    
    private String activityId;

    public CycleBanner() {

    }
    
    public String getActivityId() {
		return activityId;
	}
    
    public void setActivityId(String activityId) {
		this.activityId = activityId;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public CycleBanner(long id, String title, int noticeType, String pic,
			String url) {
		super();
		this.id = id;
		this.title = title;
		this.noticeType = noticeType;
		this.pic = pic;
		this.url = url;
	}
	@Override
	public String toString() {
		return "CycleBanner [id=" + id + ", title=" + title + ", noticeType="
				+ noticeType + ", pic=" + pic + ", url=" + url + "]";
	}

	
}
