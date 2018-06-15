package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="appVersionCheck")
public class AppVersionCheck implements Serializable{

	private static final long serialVersionUID = -8272507854515961423L;

	@ExplainAnnotation (explain = "app类型",desc = "ios,android,pad")
	private String client;

	@ExplainAnnotation(explain = "当前版本")
	private String currentVersion;

	@ExplainAnnotation(explain = "最新版本")
	private String latestVersion;

	@ExplainAnnotation(explain = "是否强制升级")
	private Boolean forceUpdate;

	@ExplainAnnotation(explain = "下载APP链接")
	private String downloadUrl;

	@ExplainAnnotation(explain = "强制升级版本")
	private String forceUpdateVersion;

	public AppVersionCheck() {

	}

	public AppVersionCheck(String client, String currentVersion,
			String latestVersion, Boolean forceUpdate, String downloadUrl,String forceUpdateVersion) {
		super();
		this.client = client;
		this.currentVersion = currentVersion;
		this.latestVersion = latestVersion;
		this.forceUpdate = forceUpdate;
		this.downloadUrl = downloadUrl;
		this.forceUpdateVersion = forceUpdateVersion;
	}

	public String getForceUpdateVersion() {
		return forceUpdateVersion;
	}

	public void setForceUpdateVersion(String forceUpdateVersion) {
		this.forceUpdateVersion = forceUpdateVersion;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(String latestVersion) {
		this.latestVersion = latestVersion;
	}

	public Boolean getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(Boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	@Override
	public String toString() {
		return "AppVersionCheck [client=" + client + ", currentVersion="
				+ currentVersion + ", latestVersion=" + latestVersion
				+ ", forceUpdate=" + forceUpdate + ", downloadUrl="
				+ downloadUrl + ",forceUpdateVersion="+forceUpdateVersion+"]";
	}
		
}
