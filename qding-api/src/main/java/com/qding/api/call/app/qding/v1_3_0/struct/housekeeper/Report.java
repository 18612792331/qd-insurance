package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper;

import java.io.Serializable;
import java.util.Arrays;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 报事
 * @author lichao
 *
 */
@XStreamAlias(value="report")
public class Report implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2225492022649320195L;
	private String id;
    private String name;
    private String projectId;
    private String projectName;
    private String building;
    private String content;
    private String[] pics;
    private String voice;
    private Integer voiceNum;
	//为当前申请处理状态
    private int status;
    private long createTime;
    private int score;
	// 0:未评价  1：已评价
	private Integer commentStatus;
	private Integer isLongHu;

    
    public Report() {
	}

	public Integer getIsLongHu() {
		return isLongHu;
	}

	public void setIsLongHu(Integer isLongHu) {
		this.isLongHu = isLongHu;
	}

	public Integer getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(Integer commentStatus) {
		this.commentStatus = commentStatus;
	}

	public Report(String id, String name, String projectId, String projectName,
			String building, String content, String[] pics, String voice,
			Integer voiceNum, int status, long createTime, int score) {
		super();
		this.id = id;
		this.name = name;
		this.projectId = projectId;
		this.projectName = projectName;
		this.building = building;
		this.content = content;
		this.pics = pics;
		this.voice = voice;
		this.voiceNum = voiceNum;
		this.status = status;
		this.createTime = createTime;
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getPics() {
		return pics;
	}

	public void setPics(String[] pics) {
		this.pics = pics;
	}

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	public Integer getVoiceNum() {
		return voiceNum;
	}

	public void setVoiceNum(Integer voiceNum) {
		this.voiceNum = voiceNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", name=" + name + ", projectId="
				+ projectId + ", projectName=" + projectName + ", building="
				+ building + ", content=" + content + ", pics="
				+ Arrays.toString(pics) + ", voice=" + voice + ", voiceNum="
				+ voiceNum + ", status=" + status + ", createTime="
				+ createTime + ", score=" + score + ", toString()="
				+ super.toString() + "]";
	}

}
