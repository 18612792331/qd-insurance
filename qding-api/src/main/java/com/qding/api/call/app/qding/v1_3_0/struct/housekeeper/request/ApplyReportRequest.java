package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request;

import java.util.Arrays;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MaxLengthValidate;
import com.qding.framework.common.smart.validate.rule.MaxValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 报事申请					
 * @author lichao
 *
 */
@Validate
public class ApplyReportRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6150702072809287120L;

	@NotNullValidate
	private String accountId;
	@NotNullValidate
	private String roomId;
	@NotNullValidate
	private String projectId;
	@NotNullValidate
	private String name;
	@NotNullValidate
	private String phone;
	@NotNullValidate
	private Integer hkMatterSource;
	@MaxLengthValidate(length=140)
	private String content;
	private String pics[];
	private String voice;
	@MaxValueValidate(value="60")
	private Integer voiceNum;

	public ApplyReportRequest() {

	}

	
	public Integer getHkMatterSource() {
		return hkMatterSource;
	}


	public void setHkMatterSource(Integer hkMatterSource) {
		this.hkMatterSource = hkMatterSource;
	}


	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

	@Override
	public String toString() {
		return "ApplyReportRequest [accountId=" + accountId + ", roomId=" + roomId
				+ ", projectId=" + projectId + ", name=" + name + ", phone="
				+ phone + ", content=" + content + ", pics="
				+ Arrays.toString(pics) + ", voice=" + voice + ", voiceNum="
				+ voiceNum + ", toString()=" + super.toString() + "]";
	}

}
