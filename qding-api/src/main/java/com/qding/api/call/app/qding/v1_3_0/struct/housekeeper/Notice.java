package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 管家notice
 * @author lichao
 *
 */
@XStreamAlias(value="notice")
public class Notice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3328396128994037755L;

	private long id;
	
	//标题
	private String title = "";
	//类型 公告 1 咨询 2
	private int type = 0;
	//公告类型 1紧急通知2通知3社区活动  ,4详情5url
	private int noticeType = 1;
	
	private String content = "";
	
	private String pic = "";
	//位置1管家页面 2首页
	private int position;
	
	private long createAt;

    private String url;


    public Notice() {

    }


	public Notice(long id, String title, int type, int noticeType,
			String content, String pic, int position, long createAt, String url) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.noticeType = noticeType;
		this.content = content;
		this.pic = pic;
		this.position = position;
		this.createAt = createAt;
		this.url = url;
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


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getNoticeType() {
		return noticeType;
	}


	public void setNoticeType(int noticeType) {
		this.noticeType = noticeType;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getPic() {
		return pic;
	}


	public void setPic(String pic) {
		this.pic = pic;
	}


	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}


	public long getCreateAt() {
		return createAt;
	}


	public void setCreateAt(long createAt) {
		this.createAt = createAt;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


}
