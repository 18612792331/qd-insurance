package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Room;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 门禁
 * @author lichao
 *
 */
@XStreamAlias(value="accessControl")
public class AccessControl implements Serializable{


	private static final long serialVersionUID = -1998674821404182033L;

	@ExplainAnnotation (explain = "记录ID",desc = "2.8版新增")
	private String id;

	@ExplainAnnotation (explain = "社区名称")
	private String projectName;

	@ExplainAnnotation (explain = "楼栋信息")
	private String building;

	@ExplainAnnotation (explain = "访客名称")
	private String name;

	@ExplainAnnotation (explain = "访客电话")
	private String phone;

	@ExplainAnnotation (explain = "来访目的")
	private String purpose;

	@ExplainAnnotation (explain = "有效开始时间")
	private long effectiveStart;

	@ExplainAnnotation (explain = "有效结束时间")
	private long effectiveEnd;

	@ExplainAnnotation (explain = "通行二维码地址")
	private String qrcodeUrl;

	@ExplainAnnotation (explain = "创建时间")
	private long createTime;

	@ExplainAnnotation (explain = "通行次数")
	private int releaseNum;

	@ExplainAnnotation (explain = "房间信息")
	private Room room;

	@ExplainAnnotation (explain = "通行证类型",desc = "APP 1.4.1 新增")
	private int accessType;

	@ExplainAnnotation (explain = "通行证密码",desc = "APP 1.4.1 新增")
	private String accessPassWord;

	public AccessControl() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public long getEffectiveStart() {
		return effectiveStart;
	}

	public void setEffectiveStart(long effectiveStart) {
		this.effectiveStart = effectiveStart;
	}

	public long getEffectiveEnd() {
		return effectiveEnd;
	}

	public void setEffectiveEnd(long effectiveEnd) {
		this.effectiveEnd = effectiveEnd;
	}

	/**
	 * @return the qrcodeUrl
	 */
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	/**
	 * @param qrcodeUrl the qrcodeUrl to set
	 */
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getReleaseNum() {
		return releaseNum;
	}

	public void setReleaseNum(int releaseNum) {
		this.releaseNum = releaseNum;
	}

	public int getAccessType() {
		return accessType;
	}

	public void setAccessType(int accessType) {
		this.accessType = accessType;
	}

	public String getAccessPassWord() {
		return accessPassWord;
	}

	public void setAccessPassWord(String accessPassWord) {
		this.accessPassWord = accessPassWord;
	}

	public AccessControl(String projectName, String building, String name,
			String phone, String purpose, long effectiveStart,
			long effectiveEnd, String qrcodeUrl, long createTime, int releaseNum,Room room,int accessType,String accessPassWord) {
		super();
		this.projectName = projectName;
		this.building = building;
		this.name = name;
		this.phone = phone;
		this.purpose = purpose;
		this.effectiveStart = effectiveStart;
		this.effectiveEnd = effectiveEnd;
		this.qrcodeUrl = qrcodeUrl;
		this.createTime = createTime;
		this.releaseNum = releaseNum;
		this.room = room;
		this.accessType = accessType;
		this.accessPassWord = accessPassWord;
	}

	@Override
	public String toString() {
		return "AccessControl [projectName=" + projectName + ", building="
				+ building + ", name=" + name + ", phone=" + phone
				+ ", purpose=" + purpose + ", effectiveStart=" + effectiveStart
				+ ", effectiveEnd=" + effectiveEnd + ", qrcodeUrl=" + qrcodeUrl
				+ ", createTime=" + createTime + ", releaseNum=" + releaseNum
				+ ", room="+room+",accessType="+accessType+",accessPassWord="+accessPassWord+",toString()=" + super.toString() + "]";
	}
	
}
